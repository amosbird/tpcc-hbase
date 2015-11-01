BASE_DIR=$(dirname $(readlink -f $0))
BASE_LIB=${BASE_DIR}/lib
source $BASE_DIR/params.sh

CP=`echo $BASE_DIR/tpcc*.jar`:`echo ${BASE_LIB}/*.jar | sed 's/ /:/g'`:/opt/tpcc
VM_ARGS="-DXmx256m -DW=$W -DC_C_ID=$C_C_ID_RUN -DC_C_LAST=$C_C_LAST_RUN -DC_OL_I_ID=$C_OL_I_ID_RUN"

ZK_ADDR=nobida143:2181
RPC_ADDR=nobida143:1988
OUTPUT=/opt/tpcc/thread-output-$3
mkdir $OUTPUT

echo "Starting terminals from Warehouse[$1] to [$2]"
nohup java $VM_ARGS -cp $CP ict.wde.hbase.tpcc.Terminal $1-$2 $ZK_ADDR $OUTPUT $RPC_ADDR $4 > $OUTPUT/$W.out 2>&1 &
