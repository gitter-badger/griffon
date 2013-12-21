/*
 * Copyright 2008-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

import static griffon.util.GriffonNameUtils.isBlank;

/**
 * Assorted utility methods and constants.
 *
 * @author Andres Almiray
 */
public final class GriffonApplicationUtils {
    private GriffonApplicationUtils() {
    }

    private static final boolean isWindows;
    private static final boolean isWindows95;
    private static final boolean isWindows98;
    private static final boolean isWindowsNT;
    private static final boolean isWindows2000;
    private static final boolean isWindows2003;
    private static final boolean isWindowsXP;
    private static final boolean isWindowsVista;
    private static final boolean isWindows7;
    private static final boolean isWindows8;

    /**
     * True if running Linux, Solaris or MacOSX
     */
    private static final boolean isUnix;

    private static final boolean isLinux;
    private static final boolean isSolaris;
    private static final boolean isMacOSX;

    private static final String osArch;
    private static final String osName;
    private static final String osVersion;
    private static final String javaVersion;
    private static final boolean is64Bit;

    private static final boolean isJdk14;
    private static final boolean isJdk15;
    private static final boolean isJdk16;
    private static final boolean isJdk17;
    private static final boolean isJdk18;

    public static final String platform;
    public static final String basePlatform;

    static {
        osArch = System.getProperty("os.arch");
        osName = System.getProperty("os.name");
        is64Bit = osArch.contains("64");

        if (osName.contains("Windows")) {
            basePlatform = "windows";
            isWindows = true;
            isLinux = false;
            isUnix = false;
            isMacOSX = false;
            isSolaris = false;
            if (osName.contains("95")) {
                isWindows95 = true;
                isWindows98 = false;
                isWindowsNT = false;
                isWindows2000 = false;
                isWindows2003 = false;
                isWindowsXP = false;
                isWindowsVista = false;
                isWindows7 = false;
                isWindows8 = false;
            } else if (osName.contains("98")) {
                isWindows95 = false;
                isWindows98 = true;
                isWindowsNT = false;
                isWindows2000 = false;
                isWindows2003 = false;
                isWindowsXP = false;
                isWindowsVista = false;
                isWindows7 = false;
                isWindows8 = false;
            } else if (osName.contains("NT")) {
                isWindows95 = false;
                isWindows98 = false;
                isWindowsNT = false;
                isWindows2000 = true;
                isWindows2003 = false;
                isWindowsXP = false;
                isWindowsVista = false;
                isWindows7 = false;
                isWindows8 = false;
            } else if (osName.contains("2003")) {
                isWindows95 = false;
                isWindows98 = false;
                isWindowsNT = false;
                isWindows2000 = false;
                isWindows2003 = true;
                isWindowsXP = true;
                isWindowsVista = false;
                isWindows7 = false;
                isWindows8 = false;
            } else if (osName.contains("XP")) {
                isWindows95 = false;
                isWindows98 = false;
                isWindowsNT = true;
                isWindows2000 = true;
                isWindows2003 = true;
                isWindowsXP = false;
                isWindowsVista = false;
                isWindows7 = false;
                isWindows8 = false;
            } else if (osName.contains("Vista")) {
                isWindows95 = false;
                isWindows98 = false;
                isWindowsNT = false;
                isWindows2000 = false;
                isWindows2003 = false;
                isWindowsXP = false;
                isWindowsVista = true;
                isWindows7 = false;
                isWindows8 = false;
            } else if (osName.contains("Windows 7")) {
                isWindows95 = false;
                isWindows98 = false;
                isWindowsNT = false;
                isWindows2000 = false;
                isWindows2003 = false;
                isWindowsXP = false;
                isWindowsVista = false;
                isWindows7 = true;
                isWindows8 = false;
            } else if (osName.equals("Windows 8")) {
                isWindows95 = false;
                isWindows98 = false;
                isWindowsNT = false;
                isWindows2000 = false;
                isWindows2003 = false;
                isWindowsXP = false;
                isWindowsVista = false;
                isWindows7 = false;
                isWindows8 = true;
            } else {
                isWindows95 = false;
                isWindows98 = false;
                isWindowsNT = false;
                isWindows2000 = false;
                isWindows2003 = false;
                isWindowsXP = false;
                isWindowsVista = false;
                isWindows7 = false;
                isWindows8 = false;
            }
        } else if (osName.contains("Linux")) {
            basePlatform = "linux";
            isWindows = false;
            isLinux = true;
            isUnix = true;
            isMacOSX = false;
            isSolaris = false;
            isWindows95 = false;
            isWindows98 = false;
            isWindowsNT = false;
            isWindows2000 = false;
            isWindows2003 = false;
            isWindowsXP = false;
            isWindowsVista = false;
            isWindows7 = false;
            isWindows8 = false;
        } else if (osName.contains("Solaris") || osName.contains("SunOS")) {
            basePlatform = "solaris";
            isWindows = false;
            isLinux = false;
            isUnix = true;
            isMacOSX = false;
            isSolaris = true;
            isWindows95 = false;
            isWindows98 = false;
            isWindowsNT = false;
            isWindows2000 = false;
            isWindows2003 = false;
            isWindowsXP = false;
            isWindowsVista = false;
            isWindows7 = false;
            isWindows8 = false;
        } else if (osName.contains("Mac OS")) {
            basePlatform = "macosx";
            isWindows = false;
            isLinux = false;
            isUnix = true;
            isMacOSX = true;
            isSolaris = false;
            isWindows95 = false;
            isWindows98 = false;
            isWindowsNT = false;
            isWindows2000 = false;
            isWindows2003 = false;
            isWindowsXP = false;
            isWindowsVista = false;
            isWindows7 = false;
            isWindows8 = false;
        } else {
            basePlatform = "unknown";
            isWindows = false;
            isLinux = false;
            isUnix = false;
            isMacOSX = false;
            isSolaris = false;
            isWindows95 = false;
            isWindows98 = false;
            isWindowsNT = false;
            isWindows2000 = false;
            isWindows2003 = false;
            isWindowsXP = false;
            isWindowsVista = false;
            isWindows7 = false;
            isWindows8 = false;
        }

        osVersion = System.getProperty("os.version");
        javaVersion = System.getProperty("java.version");
        String version = javaVersion.substring(0, 3);
        isJdk14 = true;
        switch (version) {
            case "1.8":
                isJdk18 = true;
                isJdk17 = true;
                isJdk16 = true;
                isJdk15 = true;
                break;
            case "1.7":
                isJdk18 = false;
                isJdk17 = true;
                isJdk16 = true;
                isJdk15 = true;
                break;
            case "1.6":
                isJdk18 = false;
                isJdk17 = false;
                isJdk16 = true;
                isJdk15 = true;
                break;
            case "1.5":
                isJdk18 = false;
                isJdk17 = false;
                isJdk16 = false;
                isJdk15 = true;
                break;
            default:
                isJdk18 = false;
                isJdk17 = false;
                isJdk16 = false;
                isJdk15 = false;
                break;
        }

        platform = basePlatform + (is64Bit && !isSolaris ? "64" : "");
    }

    public static boolean isWindows() {
        return isWindows;
    }

    public static boolean isWindows95() {
        return isWindows95;
    }

    public static boolean isWindows98() {
        return isWindows98;
    }

    public static boolean isWindowsNT() {
        return isWindowsNT;
    }

    public static boolean isWindows2000() {
        return isWindows2000;
    }

    public static boolean isWindows2003() {
        return isWindows2003;
    }

    public static boolean isWindowsXP() {
        return isWindowsXP;
    }

    public static boolean isWindowsVista() {
        return isWindowsVista;
    }

    public static boolean isWindows7() {
        return isWindows7;
    }

    public static boolean isWindows8() {
        return isWindows8;
    }

    public static boolean isUnix() {
        return isUnix;
    }

    public static boolean isLinux() {
        return isLinux;
    }

    public static boolean isSolaris() {
        return isSolaris;
    }

    public static boolean isMacOSX() {
        return isMacOSX;
    }

    public static String getOsArch() {
        return osArch;
    }

    public static String getOsName() {
        return osName;
    }

    public static String getOsVersion() {
        return osVersion;
    }

    public static String getJavaVersion() {
        return javaVersion;
    }

    public static boolean is64Bit() {
        return is64Bit;
    }

    public static boolean isJdk14() {
        return isJdk14;
    }

    public static boolean isJdk15() {
        return isJdk15;
    }

    public static boolean isJdk16() {
        return isJdk16;
    }

    public static boolean isJdk17() {
        return isJdk17;
    }

    public static boolean isJdk18() {
        return isJdk18;
    }

    public static String getPlatform() {
        return platform;
    }

    public static String getBasePlatform() {
        return basePlatform;
    }

    public static boolean getIsWindows() {
        return isWindows;
    }

    public static boolean getIsWindows95() {
        return isWindows95;
    }

    public static boolean getIsWindows98() {
        return isWindows98;
    }

    public static boolean getIsWindowsNT() {
        return isWindowsNT;
    }

    public static boolean getIsWindows2000() {
        return isWindows2000;
    }

    public static boolean getIsWindows2003() {
        return isWindows2003;
    }

    public static boolean getIsWindowsXP() {
        return isWindowsXP;
    }

    public static boolean getIsWindowsVista() {
        return isWindowsVista;
    }

    public static boolean getIsWindows7() {
        return isWindows7;
    }

    public static boolean getIsWindows8() {
        return isWindows8;
    }

    public static boolean getIsUnix() {
        return isUnix;
    }

    public static boolean getIsLinux() {
        return isLinux;
    }

    public static boolean getIsSolaris() {
        return isSolaris;
    }

    public static boolean getIsMacOSX() {
        return isMacOSX;
    }

    public static boolean getIs64Bit() {
        return is64Bit;
    }

    public static boolean getIsJdk14() {
        return isJdk14;
    }

    public static boolean getIsJdk15() {
        return isJdk15;
    }

    public static boolean getIsJdk16() {
        return isJdk16;
    }

    public static boolean getIsJdk17() {
        return isJdk17;
    }

    public static boolean getIsJdk18() {
        return isJdk18;
    }

    @Nonnull
    @SuppressWarnings("ConstantConditions")
    public static Locale parseLocale(@Nullable String locale) {
        if (isBlank(locale)) return Locale.getDefault();
        String[] parts = locale.split("_");
        switch (parts.length) {
            case 1:
                return new Locale(parts[0]);
            case 2:
                return new Locale(parts[0], parts[1]);
            case 3:
                return new Locale(parts[0], parts[1], parts[2]);
            default:
                return Locale.getDefault();
        }
    }
}
