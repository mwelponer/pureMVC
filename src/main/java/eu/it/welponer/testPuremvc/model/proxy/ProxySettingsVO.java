package eu.it.welponer.testPuremvc.model.proxy;

import lombok.Getter;
import lombok.Setter;

public class ProxySettingsVO {

    @Getter @Setter
    private static String proxy_host;
    @Getter @Setter
    private static int proxy_port;
    @Getter @Setter
    private static String proxy_user;
    @Getter @Setter
    private static String proxy_password;
}
