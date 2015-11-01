tpcc-hbase
==========

tpcc-hbase is the tpcc benchmark which runs on Domino. The traditional tpcc benchmark only supports RDBMS and SQL interface. The tpcc-hbase supports Domino's Key-Value interface.


1. Go to project Domino, `mvn install`, make sure you have domino's lib in your local repo
2. Back to project Tpcc-hbase, `mvn package`, you will get a `lib` directory containing all the dependencies and `tpcc-[version].jar`
3. Put your `hbase-site.xml` inside tpcc-hbase's root directory
4. Execute `create-table.sh` to set up all the needed tables
5. Edit `params.sh` to meet the test's need
6. Execute `start-pop.sh` to populate hbase with tpcc testing data
7. Execute `start-all.sh` to start tpcc test
