package fr.sogeti.entities

import javax.persistence.{Id, Basic, Column, JoinColumn, ManyToOne, Table, Entity, GeneratedValue, GenerationType}
import scala.beans.BeanProperty
import com.google.gson.annotations.{Expose, SerializedName}

/**
 * Product
 * @param ref the reference
 * @param desc the description
 * @param price1 the price
 * @param img the image
 * @param idSup the id of it's supplier
 * @param cat the category
 */
@Entity
@Table(name= "product")
class Product(ref : String, design : String, desc : String, price1 : Double, img : String) {
    
    /**
     * the product's id
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Expose
    @SerializedName("id")
    @BeanProperty
    var id : Int = _;
    
    /**
     * the product's reference
     */
    @Column(name = "reference")
    @Expose
    @SerializedName("reference")
    @BeanProperty
    var reference : String = ref;
    
    /**
     * the product's name
     */
    @Column(name = "designation")
    @Expose
    @SerializedName("designation")
    @BeanProperty
    var designation : String = design;
    
    /**
     * the product's description
     */
    @Column(name = "description")
    @Expose
    @SerializedName("description")
    @BeanProperty
    var description : String = desc;
    
    /**
     * the product's price
     */
    @Column(name = "price")
    @Expose
    @SerializedName("price")
    @BeanProperty
    var price : Double = price1;
    
    /**
     * the proudct's supplier
     */
    @JoinColumn(name = "id_supplier", referencedColumnName = "id", insertable = false, updatable = false)
    @Expose(serialize = true, deserialize = false)
    @ManyToOne
    @BeanProperty
    var supplier : Supplier = _;
    
    /**
     * the id of the product's supplier
     */
    @Basic(optional = false)
    @Column(name = "id_supplier")
    @Expose
    @SerializedName("idSupplier")
    @BeanProperty
    var idSupplier : Integer = _
    
    /**
     * the product's image 
     */
    @Column(name = "image")
    @Expose
    @SerializedName("image")
    @BeanProperty
    var image : String = img
    
    /**
     * the product's category
     */
    @JoinColumn(name = "id_category", referencedColumnName = "id", insertable = false, updatable = false)
    @Expose(serialize = false, deserialize = false)
    @ManyToOne
    @BeanProperty
    var category : Category = _;
    
    
    @Column(name = "id_category")
    @Expose
    @SerializedName("idCategory")
    @SerializedName("id_category")
    @BeanProperty
    var idCategory : Integer = _
    
    /**
     * the default empty constructor
     */
    def this() = this(null, null, null, -1, null);

    override def toString = "id : %d, reference : %s, designation : %s, description : %s, price : %s, idSup : %d, image : %s, category : %s" .format(id, reference, designation, description, price, idSupplier, image, idCategory);
}