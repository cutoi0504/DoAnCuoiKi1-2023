package QuanLyHangHoa;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

public class JDBCConnection {
	private Connection conn;
	public JDBCConnection() {
		final String url = "jdbc:mysql://localhost:3306/hello";
		final String user = "root";
		final String password = "chienvta111333";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public boolean addHangHoa (HangHoa e) {
		String sql = "INSERT INTO qlhanghoa(Id, Ten, Gia, SoLuong, DaNhap, NgayNhap, DaXuat, NgayXuat) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, e.getId());
            ps.setString(2, e.getTen());
            ps.setDouble(3, e.getGia());
            ps.setInt(4, e.getSoLuong());
            ps.setInt(5, e.getDaNhap());
            ps.setString(6, e.getNgayNhap());
            ps.setInt(7, e.getDaXuat());
            ps.setString(8, e.getNgayXuat());
            return ps.executeUpdate() > 0;   
        } catch (Exception e1) {
            e1.printStackTrace();
        }   
        return false;
	}
	
	public boolean xuatHangHoa(HangHoa e, String id, int soLuong, String ngayXuat) {
		String sql = "UPDATE qlhanghoa SET SoLuong=?, DaXuat=?, NgayXuat=? "
				+ "WHERE Id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (e.getSoLuong()-soLuong));
			ps.setInt(2, (e.getDaXuat()+soLuong));
			ps.setString(3, ngayXuat);
			ps.setString(4, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	public boolean xoaHangHoa(HangHoa e, String id) {
		String sql = "DELETE FROM qlhanghoa WHERE Id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	public boolean chinhSuaID(String id) {
		String sql = "UPDATE qlhanghoa SET Id=? WHERE Id=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			int idd=Integer.valueOf(id)-1;
			ps.setString(1, String.valueOf(Integer.valueOf(idd)));
			ps.setString(2, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	public boolean chinhSuaHangHoa(HangHoa e, String id) {
		String sql = "UPDATE qlhanghoa SET Ten=?, Gia=?, "
				+ "SoLuong=?, DaNhap=?, NgayNhap=?, DaXuat=?, NgayXuat=? "
				+ "WHERE Id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getTen());
			ps.setDouble(2, e.getGia());
			ps.setInt(3, e.getSoLuong());
			ps.setInt(4, e.getDaNhap());
			ps.setString(5, e.getNgayNhap());
			ps.setInt(6, e.getDaXuat());
			ps.setString(7, e.getNgayXuat());
			ps.setString(8, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<HangHoa> getListHangHoa(){
        ArrayList<HangHoa> list = new ArrayList<>();
        String sql = "SELECT * FROM qlhanghoa"; 
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HangHoa s = new HangHoa(rs.getString("Id"), rs.getString("Ten"), 
                		rs.getDouble("Gia"), rs.getInt("SoLuong"), 
                		rs.getInt("DaNhap"), rs.getString("NgayNhap"),
                		rs.getInt("DaXuat"), rs.getString("NgayXuat"));

                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	

	public static void main(String[] args) {
		
	}
}
