BASE_DIR=$(dirname $(readlink -f $0))
BASE_LIB=${BASE_DIR}/lib
source $BASE_DIR/params.sh
total=$W
htotal=$W
tn=$TERMINAL_PER_W
test_run=t${htotal}-$tn

echo "Starting test run for $htotal warehouses"

cd /opt/tpcc; ./start-m-center.sh $test_run
sleep 3

step=$(( $total / 4 ))
hstep=$(( $htotal / 4 ))

w=0

for i in `seq 0 3`; do

ssh nobida14$i "/opt/tpcc/start-terminals.sh $w $(( $w + $hstep - 1 )) $test_run $tn"
echo /opt/tpcc/start-terminals.sh $w $(( $w + $hstep - 1 )) $test_run $tn
w=$(( $w + $step ))

done
