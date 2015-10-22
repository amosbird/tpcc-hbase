package ict.wde.hbase.tpcc.population;

import ict.wde.hbase.tpcc.Const;
import ict.wde.hbase.tpcc.Utils;
import ict.wde.hbase.tpcc.table.Item;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class ItemPop extends DataPopulation {

  static final long POP_TOTAL_ID = 100000;
  private int id = 0;
  private HTableInterface itable;

  public ItemPop(Configuration conf, int wid) throws IOException {
    super(wid);
    conf.set(HConstants.HBASE_CLIENT_INSTANCE_ID, wid + "");
    itable = new HTable(conf, Item.TABLE);
    itable.setAutoFlush(false);
  }

  private byte[] data() {
    if (id % 10 != 0) return Utils.randomString(26, 50).getBytes();
    int p = Utils.random(26, 42);
    StringBuffer sb = new StringBuffer(50);
    sb.append(Utils.randomString(26, p));
    sb.append("ORIGINAL");
    sb.append(Utils.randomString(p, 42));
    return sb.toString().getBytes();
  }

  private byte[] imid() {
    return Integer.toString(Utils.random(1, 10000)).getBytes();
  }

  private byte[] name() {
    return Utils.randomString(14, 24).getBytes();
  }

  private byte[] price() {
    return Bytes.toBytes((long) Utils.random(100, 10000));
  }

  @Override
  public int popOneRow() throws IOException {
    if (id >= POP_TOTAL_ID) {
      itable.close();
      return 0;
    }
    byte[] i_id = Item.toRowkey(id);
    Put put = new Put(i_id);
    put.add(Const.ID_FAMILY, Item.I_ID, i_id);
    put.add(Const.TEXT_FAMILY, Item.I_IM_ID, imid());
    put.add(Const.TEXT_FAMILY, Item.I_NAME, name());
    put.add(Const.TEXT_FAMILY, Item.I_DATA, data());
    put.add(Const.NUMERIC_FAMILY, Item.I_PRICE, price());
    put(put, itable);
    ++id;
    return 1;
  }
}
