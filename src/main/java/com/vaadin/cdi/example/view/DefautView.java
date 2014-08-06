package com.vaadin.cdi.example.view;

import com.vaadin.cdi.CDIView;
import com.vaadin.event.LayoutEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.label.RichText;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
@CDIView
public class DefautView extends MVerticalLayout implements View {

    @PostConstruct
    public void initComponent() {
        
        addComponents(
                new RichText().withMarkDownResource("/welcome.md")
        );
        Panel loginPanel = new Panel("Login");
loginPanel.setWidth("250px");
        
         LoginForm login = new LoginForm();
         addComponent(login);
        // loginPanel.addComponent(new LoginForm());
           // Find the context we are running in and get the browser
        // information from that.
        final WebBrowser webBrowser = UI.getCurrent().getSession().getBrowser();

        // Create a text to show based on the browser.
    //   String browserText = getBrowserAndVersion(webBrowser);
      //  browserText = browserText + " in " + getOperatingSystem(webBrowser);

        // Create labels for the information and add them to the application

        final Label ipAddresslabel = new Label(webBrowser.getAddress());
        ipAddresslabel.setCaption("IP address");
        addComponent(ipAddresslabel);

    //    final Label browserLabel = new Label(browserText);
    //    browserLabel.setCaption("Browser");
    //    addComponent(browserLabel);

        final Label screenSize = new Label(webBrowser.getScreenWidth() + "x"
                + webBrowser.getScreenHeight());
        screenSize.setCaption("Screen size");
        addComponent(screenSize);

        final Label locale = new Label(webBrowser.getLocale().getDisplayName());
        locale.setCaption("Locale");

        // Client timezone offset w/o possible DST:
        final int rtzOffset = webBrowser.getRawTimezoneOffset();
        // DST:
        final int dst = webBrowser.getDSTSavings();

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
