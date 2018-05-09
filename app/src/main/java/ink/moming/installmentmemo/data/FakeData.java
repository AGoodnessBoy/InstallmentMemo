package ink.moming.installmentmemo.data;

import java.util.ArrayList;
import java.util.List;

public class FakeData {

    public  class Repaydata {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTotal_count() {
            return total_count;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public String getRepayed_count() {
            return repayed_count;
        }

        public void setRepayed_count(String repayed_count) {
            this.repayed_count = repayed_count;
        }

        public String getRepay_number() {
            return repay_number;
        }

        public void setRepay_number(String repay_number) {
            this.repay_number = repay_number;
        }

        String name;
        String total_count;
        String repayed_count;
        String repay_number;
    }

    List<Repaydata> data = new ArrayList<>();

    public  List<Repaydata> getData() {
        Repaydata test1 = new Repaydata();
        test1.setName("Alipay - 20180309");
        test1.setRepay_number("223.59");
        test1.setRepayed_count("2");
        test1.setTotal_count("6");

        Repaydata test2 = new Repaydata();
        test2.setName("Mipay - 20171021");
        test2.setRepay_number("596.44");
        test2.setRepayed_count("7");
        test2.setTotal_count("12");

        Repaydata test3 = new Repaydata();
        test3.setName("Alipay - 20171204");
        test3.setRepay_number("223.59");
        test3.setRepayed_count("5");
        test3.setTotal_count("6");

        Repaydata test4 = new Repaydata();
        test4.setName("JDpay - 20180109");
        test4.setRepay_number("320.4");
        test4.setRepayed_count("4");
        test4.setTotal_count("6");

        data.add(test1);
        data.add(test2);
        data.add(test3);
        data.add(test4);
        return data;
    }
}
