package ict.wde.hbase.tpcc.test;


import ict.wde.domino.common.DominoConst;
import ict.wde.domino.common.TMetaIface;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.PleaseHoldException;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;

import java.io.IOException;

import static ict.wde.domino.common.DominoConst.TRANSACTION_META;

/**
 * Created by amosb on 10/24/2015.
 */
public class test {

    public static void main(String args) throws IOException {
        HTableInterface mtable = new HTable(new Configuration(), TRANSACTION_META);
        byte[] row = DominoConst.long2TranscationRowKey(100);
        mtable.coprocessorProxy(TMetaIface.class, row).getTransactionStatus(100);
        System.out.println("hehehe");

    }
}
