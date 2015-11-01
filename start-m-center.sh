BASE_DIR=$(dirname $(readlink -f $0))
BASE_LIB=${BASE_DIR}/lib

CP=`echo $BASE_DIR/tpcc*.jar`:`echo ${BASE_LIB}/*.jar | sed 's/ /:/g'`
#VM_ARGS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=10099"
VM_ARGS="-XX:-OmitStackTraceInFastThrow"

ZK_ADDR=nobida143:2181
RPC_ADDR=nobida143:1988
OUTPUT=/opt/tpcc/thread-output

TEST_RUN=$1
OUTPUT=$OUTPUT-$TEST_RUN
mkdir $OUTPUT

nohup java $VM_ARGS -cp $CP ict.wde.hbase.tpcc.TpccMeasurement $ZK_ADDR $OUTPUT $RPC_ADDR > $OUTPUT/m-center.out 2>&1 &
