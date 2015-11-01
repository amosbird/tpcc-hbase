SCRIPT_NAME=$0
BIN_DIR=`dirname ${SCRIPT_NAME}`
BASE_DIR="${BIN_DIR}"
BASE_LIB=${BASE_DIR}/lib

CP=`echo $BASE_DIR/tpcc*.jar`:`echo ${BASE_LIB}/*.jar | sed 's/ /:/g'`

TABLE_CONF=$BASE_DIR/wd.conf
ZK_ADDR=nobida143:2181
DROP_OLD_TABLES=true

java -cp $CP ict.wde.hbase.tpcc.population.TableCreator $TABLE_CONF $ZK_ADDR $DROP_OLD_TABLES
