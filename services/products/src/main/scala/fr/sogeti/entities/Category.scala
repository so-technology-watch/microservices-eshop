package fr.sogeti.entities

import javax.persistence.{Table, Entity, OneToMany, Column, Basic, Id, GeneratedValue, GenerationType}
import java.{util => ju}
import scala.beans.BeanProperty
import com.google.gson.annotations.Expose

/**
 * Category
 * @param name1 the category's name
 * @param desc the category's description
 */
@Entity
@Table(name = "category")
class Category (name1 : String, desc : String) {

  /**
   * Identifier
   */
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Expose
  @BeanProperty
  var id : Integer = _;
  
  /**
   * Name
   */
  @Column(name = "name")
  @Expose
  @BeanProperty
  var name : String = name1;
  
  /**
   * Description
   */
  @Column(name = "description")
  @Expose
  @BeanProperty
  var description : String = desc;
  
  /**
   * Products
   */
  @OneToMany(mappedBy = "idCategory")
  @Expose
  @BeanProperty
  var products : ju.List[Product] = new ju.ArrayList[Product]();

  /**
   * Default constructor
   */
  def this() = this(null,null);
  
  /**
   * Add a product to the list of products
   * @param product the product to add
   */
  def addProduct(product : Product) : Unit = {
    products.add(product)
  }
  
  override def toString = "name : %s, description : %s, products : %s".format(name, description, products);
     
}