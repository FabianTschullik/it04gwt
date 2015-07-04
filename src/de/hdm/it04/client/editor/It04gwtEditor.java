package de.hdm.it04.client.editor;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.LoginInfo;


	/**
	 * Entry point classes define <code>onModuleLoad()</code>.
	 */
	public class It04gwtEditor implements EntryPoint {
		
		
		// TODO #05: add constants for OAuth2 (don't forget to update GOOGLE_CLIENT_ID)
		private static final Auth AUTH = Auth.get();
		private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
		private static final String GOOGLE_CLIENT_ID = "184594870148-srbcc60r0u9hfevtrtsoq47it8rt6dgp.apps.googleusercontent.com";
		private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
		// TODO #05:> end

		// TODO #06: define controls for login
		private final HorizontalPanel loginPanel = new HorizontalPanel();
		private final Anchor signInLink = new Anchor("");
		private final Image loginImage = new Image();
		private final TextBox nameField = new TextBox();
		// TODO #06:> end

		/**
		 * The message displayed to the user when the server cannot be reached or returns an error.
		 */
		private static final String SERVER_ERROR = "An error occurred while "
				+ "attempting to contact the server. Please check your network "
				+ "connection and try again.";

		/**
		 * Create a remote service proxy to talk to the server-side Greeting service.
		 */
		private final It04gwtServiceAsync greetingService = GWT.create(It04gwtService.class);

		// TODO #07: add helper methods for Login, Logout and AuthRequest

		private void loadLogin(final LoginInfo loginInfo) {
			signInLink.setHref(loginInfo.getLoginUrl());
			signInLink.setText("Please, sign in with your Google Account");
			signInLink.setTitle("Sign in");
		}

		private void loadLogout(final LoginInfo loginInfo) {
			signInLink.setHref(loginInfo.getLogoutUrl());
			signInLink.setText(loginInfo.getName());
			signInLink.setTitle("Sign out");
		}

		private void addGoogleAuthHelper() {
			final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID)
					.withScopes(PLUS_ME_SCOPE);
			AUTH.login(req, new Callback<String, Throwable>() {
				@Override
				public void onSuccess(final String token) {

					if (!token.isEmpty()) {
						greetingService.loginDetails(token, new AsyncCallback<LoginInfo>() {
							@Override
							public void onFailure(final Throwable caught) {
								GWT.log("loginDetails -> onFailure");
							}

							@Override
							public void onSuccess(final LoginInfo loginInfo) {
								signInLink.setText(loginInfo.getName());
								nameField.setText(loginInfo.getName());
								signInLink.setStyleName("login-area");
								loginImage.setUrl(loginInfo.getPictureUrl());
								loginImage.setVisible(false);
								loginPanel.add(loginImage);
								loginImage.addLoadHandler(new LoadHandler() {
									@Override
									public void onLoad(final LoadEvent event) {
										final int newWidth = 24;
										final com.google.gwt.dom.client.Element element = event
												.getRelativeElement();
										if (element.equals(loginImage.getElement())) {
											final int originalHeight = loginImage.getOffsetHeight();
											final int originalWidth = loginImage.getOffsetWidth();
											if (originalHeight > originalWidth) {
												loginImage.setHeight(newWidth + "px");
											} else {
												loginImage.setWidth(newWidth + "px");
											}
											loginImage.setVisible(true);
										}
									}
								});
							}
						});
					}
				}

				@Override
				public void onFailure(final Throwable caught) {
					GWT.log("Error -> loginDetails\n" + caught.getMessage());
				}
			});
		}

		// TODO #07:> end

		/**
		 * This is the entry point method.
		 */

		
		
		public void onModuleLoad() {
		
			
			
			
			
			// TODO #08: create login controls
			

			signInLink.getElement().setClassName("login-area");
			signInLink.setTitle("sign out");
			loginImage.getElement().setClassName("login-area");
			loginPanel.add(signInLink);
			RootPanel.get("loginPanelContainer").add(loginPanel);
			final StringBuilder userEmail = new StringBuilder();
			greetingService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
				@Override
				public void onFailure(final Throwable caught) {
					GWT.log("login -> onFailure");
				}

				@Override
				public void onSuccess(final LoginInfo result) {
					if (result.getName() != null && !result.getName().isEmpty()) {
						addGoogleAuthHelper();
						loadLogout(result);
	
						Window.alert(GWT.getHostPageBaseURL());
						Window.alert(GWT.getModuleBaseURL());
						Window.alert(GWT.getModuleName());
						
						RootPanel.get("header").add(new MenuForm());
						
						RootPanel.get("content").add(new Welcome().load());
					
						
					} else {
						loadLogin(result);
					}
					userEmail.append(result.getEmailAddress());
				}
			});
			// TODO #08:> end
			
			
			
			
			
			
			
			;
		}
	}
	
	




