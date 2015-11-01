BASE_DIR=$(dirname $(readlink -f $0))
BASE_LIB=${BASE_DIR}/lib
source $BASE_DIR/params.sh

CP=`echo $BASE_DIR/tpcc*.jar`:`echo ${BASE_LIB}/*.jar | sed 's/ /:/g'`
VM_ARGS="-DW=$W -DC_C_ID=$C_C_ID_LOAD -DC_C_LAST=$C_C_LAST_LOAD -DC_OL_I_ID=$C_OL_I_ID_LOAD"

ZK_ADDR=nobida143:2181

nohup java $VM_ARGS -DPOP_W_FROM=0 -DPOP_W_TO=$(($W-1)) -cp $CP ict.wde.hbase.tpcc.population.Main $ZK_ADDR wd > $BASE_DIR/pop$W.log 2>&1 &
