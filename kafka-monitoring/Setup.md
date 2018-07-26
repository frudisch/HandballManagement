# Test Setup

Grafana: 
``docker run -d -p 3000:3000 grafana/grafana``

InfluxDB:
``docker run -p 8086:8086 -p 8083:8083 -e INFLUXDB_ADMIN_ENABLED=true influxdb``
