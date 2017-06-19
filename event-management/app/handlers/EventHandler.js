'use strict';

exports.findAll = function (request, reply) {
    reply(this.eventRepository.findAll());
};

exports.searchEventByName = function (request, reply) {
    let name = request.query.name;

    reply(name);
};

exports.findByUUID = function (request, reply) {
    let uuid = request.params.uuid;

    reply(this.eventRepository.findByUUID(uuid));
};

exports.createEvent = function (request, reply) {
    let event = request.body;

    reply(this.eventRepository.createEvent(event));
};

exports.updateEvent = function (request, reply) {
    let uuid = request.params.uuid;
    let event = request.body;

    reply(this.eventRepository.updateEvent(uuid, event));
};

exports.deleteEvent = function (request, reply) {
    let uuid = request.params.uuid;

    reply(this.eventRepository.deleteEvent(uuid));
};