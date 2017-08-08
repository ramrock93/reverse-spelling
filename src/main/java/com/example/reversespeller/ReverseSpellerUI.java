package com.example.reversespeller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The ReverseSpellerUI class extends the UI class, representing the user
 * interface of of the reverse speller web application. </br>
 * Its main responsibility is to instantiate the UI components as well as
 * controlling the UI logic.
 * 
 * @author Ramin Esfandiari </br>
 *         8. aug. 2017 </br>
 */
@SpringUI
public class ReverseSpellerUI extends UI {

	private VerticalLayout vLayout; // A vertical layout to hold the input field, submit button and result label.
	private GridLayout grid; // A grid layout to centralize the component in middle of the page.

	private Label header; // A header label to display the application name.
	private TextField input; // A text field to be used for text input.
	private Button submit; // A submit button to submit the input value from the text field.
	private Label result; // A label to show the result of the reversed text.
	private Label resultHeader; // A small header for the result label.

	@Override
	protected void init(VaadinRequest request) {
		instantiateComponents(); // Instantiate UI components.
		setComponentsDimensions(); // Set component dimensions.
		addComponentStyleName(); // Add style names to components.
		addComponentsToLayouts(); // Add components to layouts.
		setComponentAligment(); // Set component alignments.

		submit.setClickShortcut(KeyCode.ENTER); // Set ENTER as a click shortcut for the submit button.
		submit.addClickListener(e -> {
			String inputValue = input.getValue().replaceAll(" ", "%20"); // Replaces all spaces with %20 from the input
																			// value.

			if (inputValue.equals("") || inputValue == null) { // If the input value is empty or null...
				Notification.show("Please enter in something...", Type.ASSISTIVE_NOTIFICATION); // Display notification.
			} else {

				// Call the api service and get the reversed string, given the parameter.
				String reversed = callRestService("https://reverse-speller.herokuapp.com/api/reverse/", inputValue);
				result.setValue(reversed); // Set the reversed string in the result label.
			}
		});
		setContent(grid); // Add the grid to the user interface.
	}

	/**
	 * Set component alignments in this method.
	 */
	private void setComponentAligment() {
		// Align the components inside vertical layout in middle-center.
		vLayout.setComponentAlignment(input, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(submit, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(result, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(resultHeader, Alignment.MIDDLE_CENTER);

		// Align the header in grid layout in top-center.
		grid.setComponentAlignment(header, Alignment.TOP_CENTER);
	}

	/**
	 * Add components to layouts in this method.
	 */
	private void addComponentsToLayouts() {
		vLayout.addComponents(input, submit, resultHeader, result); // Add the following components to vertical layout.
		grid.addComponent(header, 1, 0); // Add the header in column 1, row 0.
		grid.addComponent(vLayout, 1, 1); // Add the vertical layout in column 1, row 1.
	}

	/**
	 * Add component style names in this method.
	 */
	private void addComponentStyleName() {
		header.addStyleName(ValoTheme.LABEL_H1); // Add the H1 style to the label, making it big.
		header.addStyleName(ValoTheme.LABEL_BOLD); // Add the BOLD style to the label, making it bold.
		input.addStyleName(ValoTheme.TEXTFIELD_HUGE); // Add the HUGE style to the input field, making it big.
		result.addStyleName(ValoTheme.LABEL_H1); // Add the H1 style to the result field, making it big.
		result.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER); // Align the result label in center.
		submit.addStyleName(ValoTheme.BUTTON_PRIMARY); // Add the PRIMARY style to the button.
		submit.addStyleName(ValoTheme.BUTTON_HUGE); // Make the button huge.
		vLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
	}

	/**
	 * Set the component width and height in this method.
	 */
	private void setComponentsDimensions() {
		grid.setWidth("100%"); // Set its width to fill the page.
		grid.setHeight("100%"); // Set its height to fill the page.
		vLayout.setWidth("100%"); // Set its width fill the space allocated.
		input.setWidth("100%"); // Set the width of the input field to 100%, filling the allocated space.
		result.setWidth("100%"); // Set the with of the result field to 100%, filling the space allocated.
		submit.setWidth("100%"); // Set the width to 100% to fill the space allocated.
	}

	/**
	 * Instantiate UI component in this method. This method should be called before
	 * the other methods.
	 */
	private void instantiateComponents() {
		grid = new GridLayout(3, 3); // Instantiate the grid layout with a 3x3 cell structure.
		vLayout = new VerticalLayout(); // Instantiate the vertical layout.
		header = new Label("Reverse Speller"); // Instantiate the header with "Reverse Speller" as caption.
		input = new TextField("Your name:"); // Instantiate the input field with "Your name" as caption.
		resultHeader = new Label("Your name spelled backwards:"); // Instantiate the result header.
		result = new Label(); // Instantiate the result label.
		submit = new Button("Reverse"); // Instantiate the submit button with "Reverse" as caption.
	}

	/**
	 * Calls a REST service, given the URI and a parameter.
	 * 
	 * @param uri
	 *            The URI of the REST service.
	 * @param parameter
	 *            The parameter to the uri. Example:
	 *            "https://reverse-speller.herokuapp.com/api/reverse/paramter"
	 * @return A string value returned from the REST service.
	 */
	private String callRestService(String uri, String parameter) {
		RestTemplate service = new RestTemplate(); // Create a new RestTemplate instance.
		String reversed = null; // A variable to store the value returned from REST service.
		try {

			// Retrieve a representation by doing a GET on the URL
			reversed = service.getForObject(new URI(uri + parameter), String.class);
		} catch (RestClientException | URISyntaxException e1) {
			Notification.show(e1.getMessage(), Type.ERROR_MESSAGE); // Display any exception as a notification.
			e1.printStackTrace();
		}
		return reversed;
	}

}
