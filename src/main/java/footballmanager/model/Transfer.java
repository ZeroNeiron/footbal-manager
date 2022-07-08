package footballmanager.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "transfer_price")
    private BigDecimal transferPrice;

    @ManyToOne(targetEntity = Player.class)
    private Player player;

    @ManyToOne(targetEntity = Team.class)
    private Team buyerTeam;

    @ManyToOne(targetEntity = Team.class)
    private Team sellerTeam;

    public Transfer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(BigDecimal transferPrice) {
        this.transferPrice = transferPrice;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getBuyerTeam() {
        return buyerTeam;
    }

    public void setBuyerTeam(Team buyerTeam) {
        this.buyerTeam = buyerTeam;
    }

    public Team getSellerTeam() {
        return sellerTeam;
    }

    public void setSellerTeam(Team sellerTeam) {
        this.sellerTeam = sellerTeam;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", transferPrice=" + transferPrice +
                ", player=" + player +
                ", buyerTeam=" + buyerTeam +
                ", sellerTeam=" + sellerTeam +
                '}';
    }
}
