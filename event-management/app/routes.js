'use strict';

const Events = require('./handlers/EventHandler');

module.exports = [{
    method: 'GET',
    path: '/api/events',
    handler: Events.findAll
},{
    method: 'GET',
    path: '/api/events/search',
    handler: Events.searchEventByName
}, {
    method: 'GET',
    path: '/api/events/{uuid}',
    handler: Events.findByUUID
}, {
    method: 'POST',
    path: '/api/events',

    handler: Events.createEvent
}, {
    method: 'PUT',
    path: '/api/events/{uuid}',

    handler: Events.updateEvent
}, {
    method: 'DELETE',
    path: '/api/events/{uuid}',

    handler: Events.deleteEvent
}];