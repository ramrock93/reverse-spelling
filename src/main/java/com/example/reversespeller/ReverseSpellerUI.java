package com.example.reversespeller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Alignment;

@SpringUI
public class ReverseSpellerUI extends UI {

	private VerticalLayout vLayout;
	private GridLayout grid;

	private Label header;
	private TextField input;
	private Button submit;
	private Label result;
	private Label resultHeader;

	@Override
	protected void init(VaadinRequest request) {
		grid = new GridLayout(3, 3);
		grid.setWidth("100%");
		grid.setHeight("100%");

		vLayout = new VerticalLayout();
		vLayout.setWidth("100%");

		header = new Label("Reverse Speller");
		header.addStyleName(ValoTheme.LABEL_H1);
		header.addStyleName(ValoTheme.LABEL_BOLD);

		input = new TextField("Your name:");
		input.addStyleName(ValoTheme.TEXTFIELD_HUGE);

		resultHeader = new Label("Your name spelled backwards:");

		result = new Label();
		result.addStyleName(ValoTheme.LABEL_H1);

		submit = new Button("Reverse");
		submit.addStyleName(ValoTheme.BUTTON_PRIMARY);
		submit.addStyleName(ValoTheme.BUTTON_HUGE);

		vLayout.addComponents(input, submit, resultHeader, result);
		vLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		vLayout.setComponentAlignment(input, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(submit, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(result, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(resultHeader, Alignment.MIDDLE_CENTER);

		submit.addClickListener(e -> {
			String text = input.getValue().replaceAll(" ", "%20");

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

		grid.addComponent(header, 1, 0);
		grid.setComponentAlignment(header, Alignment.TOP_CENTER);
		grid.addComponent(vLayout, 1, 1);
		setContent(grid);
	}

}
