BASE_DIR=$(dirname $(readlink -f $0))
BASE_LIB=${BASE_DIR}/lib
source $BASE_DIR/params.sh
startw=0
totalw=$W
mstep=$(( $totalw / 4 ))
i=0

CP=`echo $BASE_DIR/tpcc*.jar`:`echo ${BASE_LIB}/*.jar | sed 's/ /:/g'`
VM_ARGS="-DW=$W -DC_C_ID=$C_C_ID_LOAD -DC_C_LAST=$C_C_LAST_LOAD -DC_OL_I_ID=$C_OL_I_ID_LOAD"
ZK_ADDR=nobida143:2181
echo Populating Items...
nohup java $VM_ARGS -cp $CP ict.wde.hbase.tpcc.population.Main $ZK_ADDR i > $BASE_DIR/pop-item.log 2>&1 &

for w in `seq $startw $mstep $(( $startw + $totalw - $mstep ))`; do
    from=$w
    to=$(( $w + $mstep - 1 ))
    echo "Starting on nobida14$i: $from - $to"
    ssh nobida14$i "/opt/tpcc/pop-data.sh $from $to"
    i=$(( i + 1 ))
done
