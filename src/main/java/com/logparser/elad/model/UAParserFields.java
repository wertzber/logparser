package com.logparser.elad.model;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * Created by eladw on 3/26/17.
 */
public class UAParserFields {

    private String osName;
    private String osVersion;

    private String browser;
    private String browserVersion;

    @GeneratePojoBuilder(withCopyMethod = true)
    public UAParserFields(String osName, String osVersion, String browser, String browserVersion) {
        this.osName = osName;
        this.osVersion = osVersion;
        this.browser = browser;
        this.browserVersion = browserVersion;
    }

    public UAParserFields(){
        this(null,null,null,null);
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UAParserFields{");
        sb.append("osName='").append(osName).append('\'');
        sb.append(", osVersion='").append(osVersion).append('\'');
        sb.append(", browser='").append(browser).append('\'');
        sb.append(", browserVersion='").append(browserVersion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
