/*
 * Copyright 2017-2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *     http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazon.aace.alexa.config;

import com.amazon.aace.alexa.EqualizerController.EqualizerBand;
import com.amazon.aace.alexa.EqualizerController.EqualizerBandLevel;
import com.amazon.aace.core.config.EngineConfiguration;

/**
 * A factory interface for creating Alexa configuration objects
 */
public class AlexaConfiguration {
    private static final String TAG = "AlexaConfiguration";

    /**
     * Factory method used to programmatically generate device info configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *   "deviceInfo":
     *   {
     *     "deviceSerialNumber": "<DEVICE_SERIAL_NUMBER>"
     *     "clientId": "<CLIENT_ID>",
     *     "productId": "<PRODUCT_ID>"
     *     "manufacturerName": "<MANUFACTURER_NAME>"
     *     "description": "<DESCRIPTION>"
     *   }
     * }
     * @endcode
     *
     * @param  deviceSerialNumber The device serial number used to authorize the client with AVS
     *
     * @param  clientId The client ID used to authorize the client with AVS
     *
     * @param  productId The product ID used to authorize the client with AVS
     *
     * @param  manufacturerName The manufacturer name of the product
     *
     * @param  description The description of the product
     */
    public static EngineConfiguration createDeviceInfoConfig(final String deviceSerialNumber, final String clientId,
            final String productId, final String manufacturerName, final String description) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createDeviceInfoConfigBinder(
                        deviceSerialNumber, clientId, productId, manufacturerName, description);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createDeviceInfoConfigBinder(
            String deviceSerialNumber, String clientId, String productId, String manufacturerName, String description);

    /**
     * Factory method used to programmatically generate alerts configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *   "alertsCapabilityAgent":
     *   {
     *     "databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>"
     *   }
     * }
     * @endcode
     *
     * @param  databaseFilePath The file path to the SQLite database used to store persistent alerts data.
     * The database will be created on initialization if it does not already exist.
     */
    public static EngineConfiguration createAlertsConfig(final String databaseFilePath) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createAlertsConfigBinder(databaseFilePath);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createAlertsConfigBinder(String databaseFilePath);

    /**
     * Factory method used to programmatically generate notifications configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *   "notifications":
     *   {
     *     "databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>"
     *   }
     * }
     * @endcode
     *
     * @param  databaseFilePath The file path to the SQLite database used to store persistent notifications data.
     * The database will be created on initialization if it does not already exist.
     */
    public static EngineConfiguration createNotificationsConfig(final String databaseFilePath) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createNotificationsConfigBinder(databaseFilePath);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createNotificationsConfigBinder(String databaseFilePath);

    /**
     * Factory method used to programmatically generate certified sender configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *   "certifiedSender":
     *   {
     *     "databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>"
     *   }
     * }
     * @endcode
     *
     * @param  databaseFilePath The file path to the SQLite database used to store persistent certified sender data.
     * The database will be created on initialization if it does not already exist.
     */
    public static EngineConfiguration createCertifiedSenderConfig(final String databaseFilePath) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createCertifiedSenderConfigBinder(databaseFilePath);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createCertifiedSenderConfigBinder(String databaseFilePath);

    /**
     * Factory method used to programmatically generate capabilities delegate configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *   "capabilitiesDelegate":
     *   {
     *     "databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>"
     *   }
     * }
     * @endcode
     *
     * @param  databaseFilePath The file path to the SQLite database used to store device capabilities.
     * The database will be created on initialization if it does not already exist.
     */
    public static EngineConfiguration createCapabilitiesDelegateConfig(final String databaseFilePath) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createCapabilitiesDelegateConfigBinder(databaseFilePath);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createCapabilitiesDelegateConfigBinder(String databaseFilePath);

    /**
     * Factory method used to programmatically generate CURL configuration data.
     * The 'libCurlUtils' sub-component of the global configuration supports the following options:
     * - CURLOPT_CAPATH If present, specifies a value for the libcurl property CURLOPT_CAPATH.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *     "libcurlUtils" : {
     *         "CURLOPT_CAPATH" : "<CA_CERTIFICATES_FILE_PATH>"
     *     }
     * }
     * @endcode
     *
     * @param  certsPath The file path to the directory holding CA certificates
     */
    public static EngineConfiguration createCurlConfig(String certsPath) {
        return createCurlConfig(certsPath, null, null);
    }

    /**
     * Factory method used to programmatically generate CURL configuration data.
     * The 'libCurlUtils' sub-component of the global configuration supports the following options:
     * - CURLOPT_CAPATH If present, specifies a value for the libcurl property CURLOPT_CAPATH.
     * - CURLOPT_INTERFACE if present, specifies a value for the libcurl property CURLOPT_INTERFACE.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *     "libcurlUtils" : {
     *         "CURLOPT_CAPATH" : "<CA_CERTIFICATES_FILE_PATH>",
     *         "CURLOPT_INTERFACE" : "<NETWORK_INTERFACE_NAME>"
     *     }
     * }
     * @endcode
     *
     * @param  certsPath The file path to the directory holding CA certificates
     * @param  iface The interface used for outgoing network interface.
     * This can be an network interface name, an IP address or a host name.
     */
    public static EngineConfiguration createCurlConfig(final String certsPath, final String iface) {
        return createCurlConfig(certsPath, iface, null);
    }

    /**
     * Factory method used to programmatically generate CURL configuration data.
     * The 'libCurlUtils' sub-component of the global configuration supports the following options:
     * - CURLOPT_CAPATH If present, specifies a value for the libcurl property CURLOPT_CAPATH.
     * - CURLOPT_INTERFACE if present, specifies a value for the libcurl property CURLOPT_INTERFACE.
     * - CURLOPT_PROXY if present, specifies a value for the libcurl property CURLOPT_PROXY.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *     "libcurlUtils" : {
     *         "CURLOPT_CAPATH" : "<CA_CERTIFICATES_FILE_PATH>",
     *         "CURLOPT_INTERFACE" : "<NETWORK_INTERFACE_NAME>",
     *         "CURLOPT_PROXY" : "<PROXY_ADDRESS>"
     *     }
     * }
     * @endcode
     *
     * @param  certsPath The file path to the directory holding CA certificates
     * @param  iface The interface used for outgoing network interface.
     * @param  proxy The proxy used for outgoing requests.
     * This can be an network interface name, an IP address or a host name.
     */
    public static EngineConfiguration createCurlConfig(final String certsPath, final String iface, final String proxy) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createCurlConfigBinder(certsPath, iface, proxy);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createCurlConfigBinder(String certsPath, String iface, String proxy);

    /**
     * Factory method used to programmatically generate device settings configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code{.json}
     * {
     *   "deviceSettings": {
     *     "databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>",
     *     "locales": [<LIST_OF_LOCALE_STRINGS>],
     *     "defaultLocale": "<DEFAULT_LOCALE_STRING>",
     *     "localeCombinations": [[<LOCALE_STRING_PAIR>]],
     *     "defaultTimezone": "<TIMEZONE>"
     *   }
     * }
     * @endcode
     *
     * @param databaseFilePath The file path to the SQLite database used to store persistent settings data.
     *        The database will be created on initialization if it does not already exist.
     * @param locales A list of locales supported by the device.
     * @param defaultLocale The default locale setting on the device.
     * @param defaultTimezone The default timezone setting on the device. For accepted values, refer to the accepted
     *        timezones here:
     *        https://developer.amazon.com/en-US/docs/alexa/alexa-voice-service/system.html#timezonechanged
     * @param localeCombinations A list of locale combinations supported by the device for dual-locale mode.
     *        The permitted combinations are [["en-CA","fr-CA"],["fr-CA","en-CA"],["en-US","es-US"],
     *        ["es-US","en-US"],["en-IN","hi-IN"],["hi-IN","en-IN"]]. Any locale specified in this list must also
     *        be specified in the @a locales list.
     */
    public static EngineConfiguration createDeviceSettingsConfig(final String databaseFilePath, final String[] locales,
            final String defaultLocale, final String defaultTimezone, final String[][] localeCombinations) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createDeviceSettingsConfigBinder(
                        databaseFilePath, locales, defaultLocale, defaultTimezone, localeCombinations);
            }
        };
    }

    /**
     * Factory method used to programmatically generate device settings configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code{.json}
     * {
     *   "deviceSettings": {
     *   	"databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>",
     *   	"locales": [
     *   		"en-US",
     *   		"en-GB",
     *   		"de-DE",
     *   		"en-IN",
     *   		"en-CA",
     *   		"ja-JP",
     *   		"en-AU",
     *   		"fr-FR",
     *   		"it-IT",
     *   		"es-ES",
     *   		"es-MX",
     *   		"fr-CA",
     *   		"es-US",
     *   		"hi-IN",
     *   		"pt-BR"
     *   	],
     *   	"defaultLocale": "en-US",
     *   	"localeCombinations": [
     *   		["en-CA","fr-CA"],
     *   		["fr-CA","en-CA"],
     *   		["en-US","es-US"],
     *   		["es-US","en-US"],
     *   		["en-IN","hi-IN"],
     *   		["hi-IN","en-IN"]
     *   	],
     *   	"defaultTimezone": "America/Vancouver"
     * }
     * @endcode
     *
     * @param databaseFilePath The file path to the SQLite database used to store persistent settings data.
     *        The database will be created on initialization if it does not already exist.
     */
    public static EngineConfiguration createDeviceSettingsConfig(final String databaseFilePath) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createDeviceSettingsConfigBinder(databaseFilePath,
                        new String[] {"en-US", "en-GB", "de-DE", "en-IN", "en-CA", "ja-JP", "en-AU", "fr-FR", "it-IT",
                                "es-ES", "es-MX", "fr-CA", "es-US", "hi-IN", "pt-BR"},
                        "en-US", "America/Vancouver",
                        new String[][] {{"en-CA", "fr-CA"}, {"fr-CA", "en-CA"}, {"en-US", "es-US"}, {"es-US", "en-US"},
                                {"en-IN", "hi-IN"}, {"hi-IN", "en-IN"}});
            }
        };
    }

    /**
     * Factory method used to programmatically generate device settings configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code{.json}
     * {
     *   "deviceSettings": {
     *     "databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>",
     *     "locales": [<LIST_OF_LOCALE_STRINGS>],
     *     "defaultLocale": "<DEFAULT_LOCALE_STRING>",
     *     "defaultTimezone": "<TIMEZONE>"
     *   }
     * }
     * @endcode
     *
     * @param databaseFilePath The file path to the SQLite database used to store persistent settings data.
     *        The database will be created on initialization if it does not already exist.
     * @param locales A list of locales supported by the device. The default is ["en-US","en-GB","de-DE",
     *        "en-IN","en-CA","ja-JP","en-AU","fr-FR","it-IT","es-ES","es-MX","fr-CA","es-US", "hi-IN", "pt-BR"].
     * @param defaultLocale The default locale setting on the device. The default is "en-US".
     * @param defaultTimezone The default timezone setting on the device. The default is "America/Vancouver".
     *        For accepted values, refer to the accepted timezones here:
     *        https://developer.amazon.com/en-US/docs/alexa/alexa-voice-service/system.html#timezonechanged
     */
    public static EngineConfiguration createDeviceSettingsConfig(final String databaseFilePath, final String[] locales,
            final String defaultLocale, final String defaultTimezone) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createDeviceSettingsConfigBinder(databaseFilePath, locales, defaultLocale, defaultTimezone);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createDeviceSettingsConfigBinder(String databaseFilePath, String[] locales,
            String defaultLocale, String defaultTimezone, String[][] localeCombinations);
    static private native long createDeviceSettingsConfigBinder(
            String databaseFilePath, String[] locales, String defaultLocale, String defaultTimezone);

    /**
     * @deprecated
     * Use @c AlexaConfiguration.createDeviceSettingsConfig().
     *
     * Factory method used to programmatically generate settings configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code{.json}
     * {
     *   "settings": {
     *     "databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>",
     *     "defaultAVSClientSettings": {
     *        "locale": "<LOCALE>"
     *     }
     *   }
     * }
     * @endcode
     *
     * @param  databaseFilePath The file path to the SQLite database used to store persistent settings data.
     * The database will be created on initialization if it does not already exist.
     *
     * @param  locale The current locale setting on the client
     */
    public static EngineConfiguration createSettingsConfig(final String databaseFilePath, final String locale) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createSettingsConfigBinder(databaseFilePath, locale);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createSettingsConfigBinder(String databaseFilePath, String locale);

    /**
     * @deprecated
     * Use @c AlexaConfiguration.createDeviceSettingsConfig().
     *
     * Factory method used to programmatically generate settings configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code{.json}
     * {
     *   "settings": {
     *     "databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>",
     *     "defaultAVSClientSettings": {
     *        "locale": "<LOCALE>"
     *     }
     *   }
     * }
     * @endcode
     *
     * @param  databaseFilePath The file path to the SQLite database used to store persistent settings data.
     * The database will be created on initialization if it does not already exist.
     *
     * @param  locale The current locale setting on the client
     */
    public static EngineConfiguration createSettingsConfig(String databaseFilePath) {
        return AlexaConfiguration.createSettingsConfig(databaseFilePath, "en-US");
    }

    /**
     * Factory method used to programmatically generate misc storage configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *   "miscDatabase":
     *   {
     *     "databaseFilePath": "<SQLITE_DATABASE_FILE_PATH>",
     *   }
     * }
     * @endcode
     *
     * @param  databaseFilePath The file path to the SQLite database used to store persistent misc storage data.
     * The database will be created on initialization if it does not already exist.
     */
    public static EngineConfiguration createMiscStorageConfig(final String databaseFilePath) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createMiscStorageConfigBinder(databaseFilePath);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createMiscStorageConfigBinder(String databaseFilePath);

    /**
     * Factory method used to programmatically generate speaker manager configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code{.json}
     * {
     *   "aace.alexa": {
     *      "speakerManager": {
     *          "enabled": [true|false]
     *      }
     *   }
     * }
     * @endcode
     *
     * @param enabled Enable or disable the speaker manager (default is enabled)
     */
    public static EngineConfiguration createSpeakerManagerConfig(final boolean enabled) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createSpeakerManagerConfigBinder(enabled);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createSpeakerManagerConfigBinder(boolean enabled);

    /**
     * Factory method used to programmatically generate system configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code    {.json}
     * {
     *   "aace.alexa": {
     *      "system": {
     *          "firmwareVersion": "<FIRMWARE_VERSION>"
     *      }
     *   }
     * }
     * @endcode
     *
     * @param  firmwareVersion The firmware version of the client device
     */
    public static EngineConfiguration createSystemConfig(final int firmwareVersion) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createSystemConfigBinder(firmwareVersion);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createSystemConfigBinder(int firmwareVersion);

    /**
     * Factory method used to programmatically generate encoder configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code{.json}
     * {
     *   "aace.alexa": {
     *      "speechRecognizer": {
     *          "encoder": {
     *               "name": "<ENCODER_NAME>"
     *          }
     *      }
     *   }
     * }
     * @endcode
     *
     * @param encoderName The encoder codec name to be used
     */
    public static EngineConfiguration createSpeechRecognizerConfig(final String encoderName) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createSpeechRecognizerConfigBinder(encoderName);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createSpeechRecognizerConfigBinder(String encoderName);

    public enum TemplateRuntimeTimeoutType {
        /**
         *  Display card timeout in milli seconds when Alexa completes TTS.
         *  @hideinitializer
         */
        DISPLAY_CARD_TTS_FINISHED_TIMEOUT("DISPLAY_CARD_TTS_FINISHED_TIMEOUT", "displayCardTTSFinishedTimeout"),

        /**
         *  Display card timeout in milli seconds when AudioPlayback Completes.
         *  @hideinitializer
         */
        DISPLAY_CARD_AUDIO_PLAYBACK_FINISHED_TIMEOUT(
                "DISPLAY_CARD_AUDIO_PLAYBACK_FINISHED_TIMEOUT", "displayCardAudioPlaybackFinishedTimeout"),

        /**
         *  Display card timeout in milli seconds when AudioPlayback is Stopped or Paused.
         *  @hideinitializer
         */
        DISPLAY_CARD_AUDIO_PLAYBACK_STOPPED_PAUSED_TIMEOUT(
                "DISPLAY_CARD_AUDIO_PLAYBACK_STOPPED_PAUSED_TIMEOUT", "displayCardAudioPlaybackStoppedPausedTimeout");

        /**
         * @internal
         */
        private String mName;

        /**
         * @internal
         */
        private String mKey;

        /**
         * Type used to identify a TemplateRuntime configuration type and value pair
         */
        TemplateRuntimeTimeoutType(String name, String key) {
            mName = name;
            mKey = key;
        }

        /**
         * @internal
         */
        public String toString() {
            return mName;
        }

        /**
         * @internal
         */
        public String getKey() {
            return mKey;
        }
    }

    public static class TemplateRuntimeTimeout {
        private TemplateRuntimeTimeoutType mType;
        private int mValue;

        public TemplateRuntimeTimeout(TemplateRuntimeTimeoutType type, int value) {
            mType = type;
            mValue = value;
        }

        public TemplateRuntimeTimeoutType getType() {
            return mType;
        }
        public int getValue() {
            return mValue;
        }
    }

    /**
     * Factory method used to programmatically generate template runtime configuration data.
     * This is an optional configuration. Following are the accepted keys and their description.
     * - displayCardTTSFinishedTimeout If present, specifies the values in milli seconds to control the timeout of
     * display card at the Alexa Speech.
     * - displayCardAudioPlaybackFinishedTimeout If present, specifies the values in milli seconds to control the
     * timeout of display card at the FINISHED state of AudioPlayback.
     * - displayCardAudioPlaybackStoppedPausedTimeout If present, specifies the values in milli seconds to control the
     * timeout of display card at STOP or PAUSE state of AudioPlayback. The data generated by this method is equivalent
     * to providing the following JSON values in a configuration file:
     *
     * @code{.json}
     * {
     *   "templateRuntimeCapabilityAgent": {
     *      "displayCardTTSFinishedTimeout": <TIMEOUT_IN_MS>,
     *      "displayCardAudioPlaybackFinishedTimeout": <TIMEOUT_IN_MS>,
     *      "displayCardAudioPlaybackStoppedPausedTimeout": <TIMEOUT_IN_MS>
     *   }
     * }
     * @endcode
     *
     * @param timeoutList A list of @c TemplateRuntimeTimeout type and value pairs
     *
     */
    public static EngineConfiguration createTemplateRuntimeTimeoutConfig(final TemplateRuntimeTimeout[] timeoutList) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createTemplateRuntimeTimeoutConfigBinder(timeoutList);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createTemplateRuntimeTimeoutConfigBinder(TemplateRuntimeTimeout[] timeoutList);

    /**
     * Factory method used to programmatically generate equalizer controller configuration data.
     * This is an optional configuration, and default settings will be used if configuration is not
     * provided. This method produces configuration data according to the JSON structure in the
     * sample below.
     *
     * @code{.json}
     *  "equalizer": {
     *      "bands": {
     *          "BASS": true,
     *          "MIDRANGE": false,
     *          "TREBLE": true
     *      },
     *      "defaultState": {
     *          "bands": {
     *              "BASS": 4,
     *              "TREBLE" : -1
     *          }
     *      },
     *      "minLevel": -6,
     *      "maxLevel": 6
     *  }
     * @endcode
     *
     * The configuration branches are used as follows:
     *
     * @li equalizer.bands: Specifies which bands are supported by the device and will be enabled
     *  for control with Alexa. Each child key is the name of an Alexa-supported band
     *  ("BASS", "MIDRANGE", or "TREBLE") and value is whether the device supports the band. Only
     *  bands explicitly declared supported will be enabled in the SDK and Alexa. Omitting this
     *  branch enables all bands by default.
     *
     * @li equalizer.defaultState: Describes the default or reset state of the equalizer. These
     *  settings are used to reset the equalizer with Alexa such as by saying "Alexa, reset bass."
     *  If this branch or its child is omitted, default values will be used.
     * @li equalizer.defaultState.bands: Defines the default gain level setting in dB for each
     *  supported equalizer band. Each element key is the name of a supported band and value is a
     *  level (int) specifying the default gain in dB. All of the supported bands must be provided
     *  once this branch is defined. All dB levels must obey the limits declared in
     *  "equalizer.minLevel" and "equalizer.maxLevel". Omitting this branch uses the default 0db
     *  for each band.
     *
     * @li equalizer.minLevel and equalizer.maxLevel: Integer values specifying the decibel level
     *  range on which Alexa may operate for the supported bands. The device may support a
     *  different range internally, but Alexa will know only about the limits declared here. Values
     *  should be specified as absolute amplitude gain in integer dB and scaled to the platform's
     *  internal range as necessary. If these values are omitted, the default range min -6dB and
     *  max +6dB will be used.
     *
     * @param  supportedBands The supported equalizer bands. Corresponds to the "equalizer.bands"
     *         config branch. Only bands provided will be enabled. Null @a supportedBands omits the
     *         config branch. Nonnull @a supportedBands includes the branch and declares each band
     *         in the set with a value "true".
     * @param  minLevel The minimum gain level for the equalizer bands in integer dB. Corresponds
     *         to "equalizer.minLevel".
     * @param  maxLevel The maximum gain level for the equalizer bands in integer dB. Corresponds
     *         to "equalizer.maxLevel".
     * @param  defaultBandLevels The default or reset state of the equalizer bands. Corresponds to
     *         the "equalizer.defaultState.bands" config branch. Null @a defaultBandLevels omits
     *         the config branch.
     */
    public static EngineConfiguration createEqualizerControllerConfig(final EqualizerBand[] supportedBands,
            final int minLevel, final int maxLevel, final EqualizerBandLevel[] defaultBandLevels) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createEqualizerControllerConfigBinder(supportedBands, minLevel, maxLevel, defaultBandLevels);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createEqualizerControllerConfigBinder(
            EqualizerBand[] supportedBands, int minLevel, int maxLevel, EqualizerBandLevel[] defaultBandLevels);

    /**
     * Factory method used to programmatically generate external media player configuration data.
     * The data generated by this method is equivalent to providing the following JSON
     * values in a configuration file:
     *
     * @code{.json}
     * {
     *   "aace.alexa": {
     *      "externalMediaPlayer": {
     *          "agent": "<agent>"
     *      }
     *   }
     * }
     * @endcode
     *
     * @param [in] agent The external media player agent
     */
    public static EngineConfiguration createExternalMediaPlayerConfig(final String agent) {
        return new EngineConfiguration() {
            @Override
            protected long createNativeRef() {
                return createExternalMediaPlayerConfigBinder(agent);
            }
        };
    }

    // Native Engine JNI methods
    static private native long createExternalMediaPlayerConfigBinder(String agent);
}
