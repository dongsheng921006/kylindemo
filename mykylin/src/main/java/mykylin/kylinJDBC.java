package mykylin;


import java.sql.*;

public class kylinJDBC {

    public static void main(String[] args) {
        String kylinDriver ="org.apache.kylin.jdbc.Driver";

        String kylinConn ="jdbc:kylin://hadoop102:7070/gmall";

        String kylinUser="ADMIN";

        String kylinPass="KYLIN";

        try {
            Class.forName(kylinDriver);
            Connection connection = DriverManager.getConnection(kylinConn,kylinUser,kylinPass);
            PreparedStatement preparedStatement = connection.prepareStatement("select\n" +
                    "    bp.PROVINCE_NAME,\n" +
                    "    sum(pi.PAYMENT_AMOUNT) PAYMENT_AMOUNT\n" +
                    "from DWD_FACT_PAYMENT_INFO pi\n" +
                    "join DWD_DIM_BASE_PROVINCE bp\n" +
                    "on pi.PROVINCE_ID=bp.id\n" +
                    "\n" +
                    "group by bp.PROVINCE_NAME;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+"==>"+resultSet.getInt(2));

            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
