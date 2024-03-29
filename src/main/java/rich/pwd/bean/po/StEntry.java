package rich.pwd.bean.po;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ST_ENTRY")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StEntry implements Serializable {
  /**
   * 流水號
   */
  @Id
  @SequenceGenerator(name = "entrySeq", sequenceName = "seq_entry", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entrySeq")
  @Column(name = "UID")
  private Long uid;
  /**
   * User 代號
   */
  @Column(name = "USER_ID")
  private Long userId;
  /**
   * Stock Symbol 股市代號
   */
  @NotEmpty(message = "股市代號必填")
  @Column(name = "SYMB")
  private String symb;
  /**
   * 建立時間
   */
  @Column(name = "C8T_DTM")
  private LocalDateTime c8tDtm;
  /**
   * 刪除時間
   */
  @Column(name = "DEL_DTM")
  private LocalDateTime delDtm;
  /**
   * 個股註記
   */
  @Valid
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "stEntry")
  @JsonManagedReference
  private List<StDtl> stDtlList;

  @Formula("(select COM_INFO.COM_NM from COM_INFO where COM_INFO.SYMB = SYMB)")
  private String comNm;
}
