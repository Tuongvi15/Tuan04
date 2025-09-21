package vn.edu.lab4.repo;

import vn.edu.lab4.model.TinTuc;
import vn.edu.lab4.model.Category;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class NewsDAO {
    private final DataSource ds;
    public NewsDAO(DataSource ds){ this.ds = ds; }

    public List<Category> findAllCategories(){
        String sql = "SELECT MADM, TENDANHMUC, NGUOIQUANLY, GHICHU FROM DANHMUC";
        List<Category> list = new ArrayList<>();
        try(Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Category dm = new Category();
                dm.setMadm(rs.getInt(1));
                dm.setTenDanhMuc(rs.getString(2));
                dm.setNguoiQuanLy(rs.getString(3));
                dm.setGhiChu(rs.getString(4));
                list.add(dm);
            }
        } catch (SQLException e){ e.printStackTrace(); }
        return list;
    }

    public List<TinTuc> findByCategory(int madm){
        String sql = "SELECT MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM FROM TINTUC WHERE MADM=?";
        List<TinTuc> list = new ArrayList<>();
        try(Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, madm);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    TinTuc t = new TinTuc();
                    t.setMatt(rs.getInt(1));
                    t.setTieuDe(rs.getString(2));
                    t.setNoiDungTT(rs.getString(3));
                    t.setLienKet(rs.getString(4));
                    t.setMadm(rs.getInt(5));
                    list.add(t);
                }
            }
        } catch (SQLException e){ e.printStackTrace(); }
        return list;
    }

    public void insert(TinTuc t) throws SQLException {
        String sql = "INSERT INTO TINTUC(TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES(?,?,?,?)";
        try(Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, t.getTieuDe());
            ps.setString(2, t.getNoiDungTT());
            ps.setString(3, t.getLienKet());
            ps.setInt(4, t.getMadm());
            ps.executeUpdate();
        }
    }

    public void delete(int matt) throws SQLException {
        try(Connection c = ds.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM TINTUC WHERE MATT=?")){
            ps.setInt(1, matt);
            ps.executeUpdate();
        }
    }
}
