# Using AWS Neptune 

1. copy/modify `env/flexo-mms-layer1-openmbee.env` to the neptune urls
2. change the FLEXO_MMS_ROOT_CONTEXT to where it'll be hosted (this affects the uri of the flexo metadata and links returned)
3. if FLEXO_MMS_ROOT_CONTEXT is changed, search/replace 'https://flexo.openmbee.org' in `mount/openmbee.sparql` to match
4. do a sparql update using mount/openmbee.sparql, this inits the db with flexo metadata graphs and ontology
5. `docker compose -f docker-compose-openmbee.yml up -d`, modify the environment for the layer 1 env file if necessary

# Single machine/local setup using fuseki

1. If desired, search/replace 'http://layer1-service' in mount/cluster.trig with where flexo will be hosted
   1. modify FLEXO_MMS_ROOT_CONTEXT in `env/flexo-mms-layer1.env`
2. `docker compose up -d`

# Stopping containers/start over

1. use `docker compose stop` in the same dir to stop containers (fuseki data will remain)
2. use `docker compose down` in the same dir to remove containers (fuseki data will be lost unless other mounts are added)

# Initial flexo sysmlv2 org setup

Import `flexo-sysmlv2.postman_collection.json` into postman

In the collection variables tab, change the current value of `host` and `flexoHost` to the deployed host, save the tab

For a fresh setup (brand new db), run the first request in the `sysmlv2.` to create the flexo org that sysmlv2 service will use

This is defaulted to `sysmlv2`, it can be changed by setting FLEXO_SYSMLV2_ORG in `env/flexo-sysmlv2.env`, and changing the postman request to match (`PUT {{flexoHost}}/orgs/{sysmlv2Id}`)

The auth is already included in the collection and is the same as what's in FLEXO_AUTH in `env/flexo-sysmlv2.env` (this is good until Jan 2026)

