package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BaseDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    //获得连接
    private void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/sis";
            conn = DriverManager.getConnection(url,"root","ww048175");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //关闭
    public void close(){
        if(rs!=null)
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if(ps!=null)
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if(conn!=null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public boolean execute(String sql,Object...objects){
        try {
            this.getConnection();
            ps = conn.prepareStatement(sql);
            if(objects!=null)//设置参数
                for(int i=0;i<objects.length;i++){
                    ps.setObject(i+1,objects[i]);
                }
            return ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return false;
    }
    //更新--增加，修改，删除
    public int executeUpdate(String sql,Object...objects){
        try {
            this.getConnection();
            ps = conn.prepareStatement(sql);
            if(objects!=null)//设置参数
                for(int i=0;i<objects.length;i++){
                    ps.setObject(i+1,objects[i]);
                }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return -1;
    }
    //查询
    public ResultSet executeQuery(String sql,Object...objects){
        try {
            this.getConnection();
            ps = conn.prepareStatement(sql);
            if(objects!=null)//设置参数
                for(int i=0;i<objects.length;i++){
                    ps.setObject(i+1,objects[i]);
                }
            return rs=ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  void deleteById(String[] id, String tlk){
        if(id.length>0) {
            String sql = "delete from ";
            sql+=tlk;
            sql+=" where id in(";
            for (int i = 0; i < id.length; i++) {
                sql += id[i] + ',';
            }
            sql = sql.substring(0, sql.length() - 1) + ')';
            try {
                this.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public long getLong(String sql){
        ResultSet rs = this.executeQuery(sql);
        try {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
