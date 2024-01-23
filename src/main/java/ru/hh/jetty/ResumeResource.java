package ru.hh.jetty;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Path("/resumes")
@Singleton
public class ResumeResource {

  private final Map<Integer, Resume> resumeDb = new HashMap<>();

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Resume createResume(CreateResume createResume) {
    var id = ThreadLocalRandom.current().nextInt(10, 100);
    var resume = new Resume(id, createResume.title());
    resumeDb.put(id, resume);
    return resume;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{resumeId}")
  public Response findById(@PathParam("resumeId") Integer id) {
    var resume = resumeDb.get(id);
    if (resume == null) {
      return Response.status(NOT_FOUND).build();
    }

    return Response.ok(resume).build();
  }

}
