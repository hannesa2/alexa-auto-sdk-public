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

/*********************************************************
**********************************************************
**********************************************************

THIS FILE IS AUTOGENERATED. DO NOT EDIT

**********************************************************
**********************************************************
*********************************************************/

#ifndef SPEECHRECOGNIZER_STARTCAPTUREMESSAGE_H
#define SPEECHRECOGNIZER_STARTCAPTUREMESSAGE_H

#include <string>
#include <vector>

#include <AACE/Engine/Utils/UUID/UUID.h>
#include <nlohmann/json.hpp>
#include "AASB/Message/Alexa/SpeechRecognizer/Initiator.h"

namespace aasb {
namespace message {
namespace alexa {
namespace speechRecognizer {

//Class Definition
struct StartCaptureMessage {
    struct Header {
        struct MessageDescription {
            static const std::string& topic() {
                static std::string topic = "SpeechRecognizer";
                return topic;
            }
            static const std::string& action() {
                static std::string action = "StartCapture";
                return action;
            }
        };
        static const std::string& version() {
            static std::string version = "3.0";
            return version;
        }
        static const std::string& messageType() {
            static std::string messageType = "Publish";
            return messageType;
        }
        std::string id = aace::engine::utils::uuid::generateUUID();
        MessageDescription messageDescription;
    };
    struct Payload {
        using Initiator = ::aasb::message::alexa::Initiator;

        Initiator initiator;
        int keywordBegin;
        int keywordEnd;
        std::string keyword;
    };
    static const std::string& topic() {
        static std::string topic = "SpeechRecognizer";
        return topic;
    }
    static const std::string& action() {
        static std::string action = "StartCapture";
        return action;
    }
    static const std::string& version() {
        static std::string version = "3.0";
        return version;
    }
    static const std::string& messageType() {
        static std::string messageType = "Publish";
        return messageType;
    }
    std::string toString() const;
    Header header;
    Payload payload;
};

//JSON Serialization
inline void to_json(nlohmann::json& j, const StartCaptureMessage::Payload& c) {
    j = nlohmann::json{
        {"initiator", c.initiator},
        {"keywordBegin", c.keywordBegin},
        {"keywordEnd", c.keywordEnd},
        {"keyword", c.keyword},
    };
}
inline void from_json(const nlohmann::json& j, StartCaptureMessage::Payload& c) {
    j.at("initiator").get_to(c.initiator);
    j.at("keywordBegin").get_to(c.keywordBegin);
    j.at("keywordEnd").get_to(c.keywordEnd);
    j.at("keyword").get_to(c.keyword);
}

inline void to_json(nlohmann::json& j, const StartCaptureMessage::Header::MessageDescription& c) {
    j = nlohmann::json{
        {"topic", c.topic()},
        {"action", c.action()},
    };
}
inline void from_json(const nlohmann::json& j, StartCaptureMessage::Header::MessageDescription& c) {
}

inline void to_json(nlohmann::json& j, const StartCaptureMessage::Header& c) {
    j = nlohmann::json{
        {"version", c.version()},
        {"messageType", c.messageType()},
        {"id", c.id},
        {"messageDescription", c.messageDescription},
    };
}
inline void from_json(const nlohmann::json& j, StartCaptureMessage::Header& c) {
    j.at("id").get_to(c.id);
    j.at("messageDescription").get_to(c.messageDescription);
}

inline void to_json(nlohmann::json& j, const StartCaptureMessage& c) {
    j = nlohmann::json{
        {"header", c.header},
        {"payload", c.payload},
    };
}
inline void from_json(const nlohmann::json& j, StartCaptureMessage& c) {
    j.at("header").get_to(c.header);
    j.at("payload").get_to(c.payload);
}

inline std::string StartCaptureMessage::toString() const {
    nlohmann::json j = *this;
    return j.dump(3);
}

}  // namespace speechRecognizer
}  // namespace alexa
}  // namespace message
}  // namespace aasb

#endif  // SPEECHRECOGNIZER_STARTCAPTUREMESSAGE_H