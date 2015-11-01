package ict.wde.hbase.tpcc.test;

import ict.wde.domino.common.DominoConst;
import ict.wde.domino.common.TMetaIface;
import ict.wde.hbase.driver.HBaseConnection;
import ict.wde.hbase.tpcc.domino.DominoDriver;
import ict.wde.hbase.tpcc.table.Warehouse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.*;

import static ict.wde.domino.common.DominoConst.TRANSACTION_META;
public class ScanTables {
  public static void main(String[] args) throws Exception {

    Configuration conf = new Configuration();
    conf.set(DominoConst.ZK_PROP, "nobida143:2181");
    HTableInterface mtable = new HTable(conf, TRANSACTION_META);
    byte[] row = DominoConst.long2TranscationRowKey(0);
    mtable.coprocessorProxy(TMetaIface.class, row).getTransactionStatus(0);
    System.out.println("hehehe");
  }
}
