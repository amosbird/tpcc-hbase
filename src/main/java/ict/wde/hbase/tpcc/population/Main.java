package ict.wde.hbase.tpcc.population;

import ict.wde.domino.common.DominoConst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.hadoop.conf.Configuration;

public class Main {

  static List<DataPopulation> tables = new ArrayList<>();
  static int wb;
  static int we;
  static {
    try {
      wb = Integer.parseInt(System.getProperty("POP_W_FROM"));
      we = Integer.parseInt(System.getProperty("POP_W_TO"));
    } catch (Exception e) {

    }
  }

  public static void main(String[] args) {
    String zkAddr = args[0];
    String tabChars = args[1];
    Configuration config = new Configuration();
    config.set(DominoConst.ZK_PROP, zkAddr);
    try {
      initTablePopulation(tabChars, config);
    } catch (IOException e) {
      e.printStackTrace();
    }
    ExecutorService es = Executors.newFixedThreadPool(48);
    try {
      es.invokeAll(tables);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    es.shutdown();
  }

  private static void initTablePopulation(String chars, Configuration config) throws IOException {
    Set<Character> hash = new HashSet<>();
    for (char c : chars.toCharArray()) {
      if (hash.contains(c)) continue;
      switch (c) {
      case 'i':
        tables.add(new ItemPop(config, 0));
        break;
      case 'w':
        for (int i = wb; i <= we; i++) {
          tables.add(new WarehousePop(config, i));
        }
        break;
      case 's':
        for (int i = wb; i <= we; i++) {
          tables.add(new StockPop(config, i));
        }
        break;
      case 'd':
        for (int i = wb; i <= we; i++) {
          tables.add(new DistrictPop(config, i));
        }
        break;
      case 'c':
        for (int i = wb; i <= we; i++) {
          tables.add(new CustomerPop(config, i));
        }
        break;
      case 'o':
        for (int i = wb; i <= we; i++) {
          tables.add(new OrderPop(config, i));
        }
        break;
      default:
        continue;
      }
      hash.add(c);
    }
  }
}
