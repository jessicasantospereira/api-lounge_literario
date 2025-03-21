#!/bin/sh

set -e

# Ajuste os detalhes de conexão conforme necessário
host="mysql"
port="3306"
user="root"
password="123456"
database="lounge_literario"

until mysql --host=$host --port=$port --user=$user --password=$password --database=$database --execute="SELECT 1" &>/dev/null; do
    >&2 echo "MySQL is unavailable - sleeping"
    sleep 1
done

>&2 echo "MySQL is up - executing command"
