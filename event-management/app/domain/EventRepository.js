'use strict';
const Event = require('./entity/Event');

exports.findAll = function () {
    return Event.find();
};

exports.findByUUID = function (uuid) {
    let query = {
        uuid: uuid
    };

    return Event.find(query);
};

exports.createEvent = function (event) {
    let newEvent = Event({
        uuid: event.uuid,
        eventName: event.eventName,
        teamUUID: event.teamUUID,
        repeatable: event.repeatable,
        location: event.location
    });

    return newEvent.save();
};

exports.updateEvent = function (uuid, event) {
    let query = {
        uuid: uuid
    };

    let existingEvent = Event.find(query);

    if (existingEvent === undefined) {
        return new Error('Event does not exist');
    }

    existingEvent.uuid = event.uuid;
    existingEvent.eventName = event.eventName;
    existingEvent.teamUUID = event.teamUUID;
    existingEvent.repeatable = event.repeatable;
    existingEvent.location = event.location;

    return existingEvent.save();
};

exports.deleteEvent = function (uuid) {
    let query = {
        uuid: uuid
    };

    let existingEvent = Event.find(query);

    if (existingEvent === undefined) {
        return new Error('Event does not exist');
    }

    return existingEvent.delete();
};