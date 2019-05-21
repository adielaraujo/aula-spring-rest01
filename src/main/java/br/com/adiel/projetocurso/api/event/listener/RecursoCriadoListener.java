package br.com.adiel.projetocurso.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.adiel.projetocurso.api.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {

		HttpServletResponse response = event.getResponse();
		Integer codigo = event.getCodigo();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();

		response.setHeader("Location", uri.toASCIIString());

	}

}
