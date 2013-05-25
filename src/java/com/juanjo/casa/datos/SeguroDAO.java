/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juanjo.casa.datos;

import com.juanjo.casa.negocio.Seguro;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jj
 */
public interface SeguroDAO {
   Seguro create();
   void insert(Seguro seguro);
   Seguro get(int idSeguro);
   void update(Seguro seguro);
   void delete(int idSeguro);
   List<Seguro> find(Map<String,Object> filter);
   
    
}
