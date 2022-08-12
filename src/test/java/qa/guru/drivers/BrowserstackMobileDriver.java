package qa.guru.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.guru.config.CredentialsConfig;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {
    static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.user", credentialsConfig.user());
        mutableCapabilities.setCapability("browserstack.key", credentialsConfig.key());
        mutableCapabilities.setCapability("app", credentialsConfig.app());
        mutableCapabilities.setCapability("device", credentialsConfig.device());
        mutableCapabilities.setCapability("os_version", credentialsConfig.os_version());
        mutableCapabilities.setCapability("project", credentialsConfig.project());
        mutableCapabilities.setCapability("build", credentialsConfig.build());
        mutableCapabilities.setCapability("name", credentialsConfig.name());

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
