package com.example.reversespeller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.LayoutFocusTraversalPolicy;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class ReverseSpellerUI extends UI {

	private VerticalLayout vLayout;
	private HorizontalLayout hLayout;

	private TextField input;
	private Button submit;
	private Label result;

	@Override
	protected void init(VaadinRequest request) {
		vLayout = new VerticalLayout();
		hLayout = new HorizontalLayout();

		input = new TextField("Text to reverse:");
		result = new Label("Result:");
		submit = new Button("Reverse");

		hLayout.addComponents(input, submit);
		hLayout.addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		hLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		vLayout.addComponents(hLayout, result);

		submit.addClickListener(e -> {
			String text = input.getValue();

			RestTemplate service = new RestTemplate();
			String reversed = null;
			try {
				reversed = service.getForObject(new URI("https://revese-speller.herokuapp.com/api/reverse/" + text),
						String.class);
			} catch (RestClientException | URISyntaxException e1) {
				Notification.show(e1.getMessage());
				e1.printStackTrace();
			}
			result.setValue(reversed);
		});

		setContent(vLayout);
	}

}
