package com.example.myapplication;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
public class MyUI extends UI {

	@SuppressWarnings("unchecked")
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		final HorizontalLayout layout1 = new HorizontalLayout();
		final TextField name = new TextField();
		name.setCaption("Usuarios:");

		final Table table = new Table("The Brightest Stars");

		// Define two columns for the built-in container
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Mag", Float.class, null);
		table.addContainerProperty("", Button.class, null);

		for (int n = 5; n <= 10; n++) {
			Button edit = new Button("Editar");
			
			table.addItem(new Object[] { "Algun nombre, je", n + 0.05f, edit }, n);
			edit.addClickListener(e -> {
				int m=14;
				Button editP = new Button("Editar");
				edit.setData(m);
				//layout.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
				table.addItem(new Object[] { "algoasdasdas", m + 0.05f, editP	 }, edit.getId());
				editP.addClickListener(d ->{
					TextField asd = new TextField();
					layout1.addComponent(asd);
				});
			});

		}
		Tree menu = new Tree();
		
		// Coupsle of childless root items
		menu.addItem("Mercury");
		menu.addItem("Algo");
		menu.setParent("Algo", "Mercury");
		menu.setChildrenAllowed("Algo", false);
		menu.addItem("Venus");
		menu.setChildrenAllowed("Venus", false);

		// An item with hierarchy
		menu.addItem("Earth");
		menu.addItem("The Moon");
		menu.setChildrenAllowed("The Moon", false);
		menu.setParent("The Moon", "Earth");
		menu.expandItem("Earth"); // Expand progra
		// Show exactly the currently contained rows (items)
		table.setPageLength(table.size());

		layout1.addComponents(name, table, menu);
		layout.setMargin(true);
		layout.setSpacing(true);

		setContent(layout);
		setContent(layout1);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
