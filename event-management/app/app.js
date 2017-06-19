'use strict';

const Hapi = require('hapi');
const Mongoose = require('mongoose');
const EventRepository = require('./domain/EventRepository');

const server = new Hapi.Server();
server.connection({ port: 3000, host: 'localhost' });

server.route(require('./routes'));

server.bind({ eventRepository: EventRepository });

Mongoose.connect('mongodb://localhost:27017/events-db');

server.start((err) => {

    if (err) {
        throw err;
    }
    console.log(`Server running at: ${server.info.uri}`);
});