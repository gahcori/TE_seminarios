package com.emergentes.controlador;

import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps;
        ConexionDB canal = new ConexionDB();
        Connection con = canal.conectar();
        ResultSet rs;
        ArrayList<Seminario> lista = new ArrayList<Seminario>();
        int id;

        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        if (op.equals("list")) {
            //Operacion para listar datos
            String sql = "select * from seminarios";
            try {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Seminario semi = new Seminario();
                    semi.setId(rs.getInt("id"));
                    semi.setTitulo(rs.getString("titulo"));
                    semi.setExpositor(rs.getString("expositor"));
                    semi.setFecha(rs.getString("fecha"));
                    semi.setHora(rs.getString("hora"));
                    semi.setCupo(rs.getInt("cupo"));

                    lista.add(semi);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (op.equals("nuevo")) {
            //Operacion para despleglar formulario
            Seminario se = new Seminario();

            request.setAttribute("semi", se);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }

        if (op.equals("editar")) {
            //Operacion para editar un registro
            id = Integer.parseInt(request.getParameter("id"));
            String sql = "select * from seminarios where id = ?";
            try {
                Seminario sem = new Seminario();

                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (rs.next()) {
                    sem.setId(rs.getInt("id"));
                    sem.setTitulo(rs.getString("titulo"));
                    sem.setExpositor(rs.getString("expositor"));
                    sem.setFecha(rs.getString("fecha"));
                    sem.setHora(rs.getString("hora"));
                    sem.setCupo(rs.getInt("cupo"));
                }
                request.setAttribute("semi", sem);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (op.equals("eliminar")) {
            //Operacion para eliminar un registro
            id = Integer.parseInt(request.getParameter("id"));
            String sql = "delete from seminarios where id = ?";
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);

                ps.executeUpdate();
                response.sendRedirect("MainController");
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String expositor = request.getParameter("expositor");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        int cupo = Integer.parseInt(request.getParameter("cupo"));

        Seminario semi = new Seminario();
        semi.setId(id);
        semi.setTitulo(titulo);
        semi.setExpositor(expositor);
        semi.setFecha(fecha);
        semi.setHora(hora);
        semi.setCupo(cupo);

        ConexionDB canal = new ConexionDB();
        Connection con = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;

        if (id == 0) {
            //Insertar registro
            String sql = "insert into seminarios (titulo,expositor,fecha,hora,cupo) values (?, ?, ?, ?, ?)";
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, semi.getTitulo());
                ps.setString(2, semi.getExpositor());
                ps.setString(3, semi.getFecha());
                ps.setString(4, semi.getHora());
                ps.setInt(5, semi.getCupo());

                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //Actualizar registro
            String sql1 = "update seminarios set titulo= ? , expositor = ?, fecha = ?, hora = ?, cupo = ? where id = ?";
            try {
                ps = con.prepareStatement(sql1);
                ps.setString(1, semi.getTitulo());
                ps.setString(2, semi.getExpositor());
                ps.setString(3, semi.getFecha());
                ps.setString(4, semi.getHora());
                ps.setInt(5, semi.getCupo());
                ps.setInt(6, semi.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect("MainController");
    }
}
