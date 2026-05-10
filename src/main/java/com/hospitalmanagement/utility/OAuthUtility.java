package com.hospitalmanagement.utility;


import com.hospitalmanagement.enums.OAuthProviderType;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuthUtility {

    public static String getProviderId(OAuth2User user, String provider) {

        switch (provider.toLowerCase()) {

            case "google":
                return user.getAttribute("sub");

            case "github":
                Object id = user.getAttribute("id");
                return id != null ? id.toString() : null;

            default:
                return null;
        }
    }

    public static OAuthProviderType getProviderType(String provider) {

        switch (provider.toLowerCase()) {

            case "google":
                return OAuthProviderType.GOOGLE;

            case "github":
                return OAuthProviderType.GITHUB;

            default:
                return null;
        }
    }

    public static String getUserEmail(OAuth2User user, String provider) {

        switch (provider.toLowerCase()) {

            case "google":
                return user.getAttribute("email");

            case "github":
                return user.getAttribute("email");

            default:
                return null;
        }
    }

    public static String getFirstName(OAuth2User user, String provider) {

        switch (provider.toLowerCase()) {

            case "google":
                return user.getAttribute("given_name");

            case "github":
                String name = user.getAttribute("name");
                return name != null ? name.split(" ")[0] : null;

            default:
                return null;
        }
    }

    public static String getLastName(OAuth2User user, String provider) {

        switch (provider.toLowerCase()) {

            case "google":
                return user.getAttribute("family_name");

            case "github":
                String name = user.getAttribute("name");

                if (name != null && name.contains(" ")) {
                    return name.substring(name.indexOf(" ") + 1);
                }

                return null;

            default:
                return null;
        }
    }

}
