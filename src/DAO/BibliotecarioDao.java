package DAO;

import model.Bibliotecario;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BibliotecarioDao {
	
	@SuppressWarnings("finally")
	public List<Bibliotecario> getAll(){
		String sql="select * from Bibliotecario";
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rset=null;
		List<Bibliotecario> bibliotecarios=new ArrayList<>();
		
		try {
			conn=ConexaoDAO.conectarBD();
			pstm=conn.prepareStatement(sql);
			rset=pstm.executeQuery();
			
			while(rset.next()) {
				String email=rset.getString("email");
				int senha=rset.getInt("senha");
				Bibliotecario bibliotecario=new Bibliotecario(email,senha);
				bibliotecario.setId(rset.getInt("Id"));
				bibliotecarios.add(bibliotecario);
			}
			conn.close();
			pstm.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			return bibliotecarios;
		}
	}
	
	public Bibliotecario getOneById(int id) {
		String sql="select * from Bibliotecario where id=?";
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rset=null;

		try {		
			conn=ConexaoDAO.conectarBD();
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset=pstm.executeQuery();

			if(rset.next()) {
				String email=rset.getString("email");
				int senha=rset.getInt("senha");
				Bibliotecario bibliotecario=new Bibliotecario(email,senha);
				bibliotecario.setId(rset.getInt("Id"));
				return bibliotecario;
			}
			conn.close();
			pstm.close();
			rset.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void delete(int id) {
		String sql="delete from Bibliotecario where id=?";
		Connection conn=null;
		PreparedStatement pstm=null;
		
		try {
			conn=ConexaoDAO.conectarBD();
			pstm=conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
			conn.close();
			pstm.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void create(Bibliotecario bibliotecario){
		String sql="insert into Bibliotecario(email,senha) values(?,?)";
		Connection conn=null;
		PreparedStatement pstm=null;
		
		try {
			conn=ConexaoDAO.conectarBD();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, bibliotecario.getEmail());
			pstm.setInt(2, bibliotecario.getSenha());
			pstm.execute();
			conn.close();
			pstm.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void update(Bibliotecario bibliotecario) {
		String sql="update Bibliotecario set email=?,senha=? where id=?";
		Connection conn=null;
		PreparedStatement pstm=null;
		
		try {
			conn=ConexaoDAO.conectarBD();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1,bibliotecario.getEmail());
			pstm.setInt(2, bibliotecario.getSenha());
			pstm.setInt(3, bibliotecario.getId());
			pstm.executeUpdate();
			conn.close();
			pstm.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}