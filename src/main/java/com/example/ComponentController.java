package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.simple.SimpleHttpResponseFactory;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

@ExecuteOn(TaskExecutors.IO)
@Controller("/component")
public class ComponentController {

	@Inject
	ComponentRepository componentRepository;

	@Get("/{uuid}")
	public HttpResponse<byte[]> icon(@PathVariable String uuid)  {
		System.out.println("Request for uuid " + uuid);
		var component = componentRepository.componentById(uuid);
		if (component.isEmpty()) {
			return HttpResponse.notFound();
		}
		return new SimpleHttpResponseFactory().status(HttpStatus.OK, component.get().getId().getBytes(StandardCharsets.UTF_8)).contentType(MediaType.TEXT_PLAIN);
	}
}
