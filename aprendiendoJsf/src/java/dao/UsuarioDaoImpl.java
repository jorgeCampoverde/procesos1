/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Usuario;
import org.hibernate.Session;
import util.NewHibernateUtil;

/**
 *
 * @author Dalton
 */
public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario model = null;
        Session sesion = NewHibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Usuario WHERE usuario = '" + usuario.getUsuario() + "'";
        try {
            sesion.beginTransaction();
            model = (Usuario) sesion.createQuery(sql).uniqueResult();
        } catch (Exception e) {
            System.out.println(e);
        }
        return model;
    }

    @Override
    public Usuario login(Usuario usuario) {
        Usuario model = this.findByUsuario(usuario);
        if (model != null) {
            if (!usuario.getClave().equals(model.getClave())) {
                model = null;
            }
        }
        return model;
    }

}
