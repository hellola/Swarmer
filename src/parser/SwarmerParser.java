/* Generated By:JavaCC: Do not edit this line. SwarmerParser.java */
package parser;
import java.io.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import com.evo.componentagent.*;
import com.evo.componentagent.util.SwarmerEntity;
import com.evo.componentagent.util.SwarmerApplication;
import com.evo.componentagent.util.NeighbourhoodData;
import com.evo.componentagent.components.*;
public class SwarmerParser implements SwarmerParserConstants {
  public static void main(String args[])
  {
      try
      {
        SwarmerApplication application = new SwarmerParser(new FileInputStream(args[0])).CompilationUnit();
        System.out.println("SUCCESS");
      }
      catch (ParseException e)
      {
        System.out.println("Exception:" + e.getMessage());
      }
      catch (FileNotFoundException e)
      {
        System.out.println(e.getMessage());
      }
  }

  final public SwarmerApplication CompilationUnit() throws ParseException {
  SwarmerApplication application = null;
    application = Program();
    jj_consume_token(0);
    {if (true) return application;}
    throw new Error("Missing return statement in function");
  }

  final public SwarmerApplication Program() throws ParseException {
  ArrayList<NeighbourhoodData> locales = null;
  ArrayList<SwarmerEntity > entities = new ArrayList<SwarmerEntity>();
  ArrayList<BehaviourOptions > behaviours = new ArrayList<BehaviourOptions >();
  BehaviourOptions behaviour;
  SwarmerEntity entity;
  SwarmerApplication application = new SwarmerApplication();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      locales = Views();
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 10:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      entity = Entity();
    entities.add(entity);
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 13:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      behaviour = Behaviour();
                             behaviours.add(behaviour);
    }
    application.setBehaviours(behaviours);
    application.setEntities(entities);
    application.setLocales(locales);
    {if (true) return application;}
    throw new Error("Missing return statement in function");
  }

  final public java.util.ArrayList<NeighbourhoodData > Views() throws ParseException {
  java.util.ArrayList<NeighbourhoodData > views = new java.util.ArrayList<NeighbourhoodData>();
  NeighbourhoodData data;
    jj_consume_token(1);
    jj_consume_token(2);
    label_4:
    while (true) {
      data = View();
                                 views.add(data);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_4;
      }
    }
    jj_consume_token(3);
    {if (true) return views;}
    throw new Error("Missing return statement in function");
  }

  final public NeighbourhoodData View() throws ParseException {
  NeighbourhoodData data = new NeighbourhoodData();
  String key;
  Integer number;
  Token tKey;
  Token tNumber;
  Token tEntity;
  Token tShape;
    tKey = jj_consume_token(IDENTIFIER);
                          data.setName(tKey.image);
    jj_consume_token(4);
    jj_consume_token(2);
    jj_consume_token(5);
    jj_consume_token(4);
    tNumber = jj_consume_token(NUMBER);
    jj_consume_token(6);
    data.setSize(Integer.parseInt(tNumber.image));
    data.setShapeFromString("circle");
    jj_consume_token(7);
    jj_consume_token(4);
    tEntity = jj_consume_token(IDENTIFIER);
                                            data.addEligibleEntity(tEntity.image);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 8:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_5;
      }
      jj_consume_token(8);
      tEntity = jj_consume_token(IDENTIFIER);
                                 data.addEligibleEntity(tEntity.image);
    }
    jj_consume_token(6);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 9:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_6;
      }
      jj_consume_token(9);
      jj_consume_token(4);
      tShape = jj_consume_token(IDENTIFIER);
      jj_consume_token(6);
                                                data.setShapeFromString(tShape.image);
    }
    jj_consume_token(3);
   {if (true) return data;}
    throw new Error("Missing return statement in function");
  }

  final public SwarmerEntity Entity() throws ParseException {
  SwarmerEntity entity = new SwarmerEntity();
  Token tName;
  Token tBehaviour, tWeight;
  java.util.Map.Entry<String,String > attribute;
    jj_consume_token(10);
    tName = jj_consume_token(IDENTIFIER);
                                    entity.setName(tName.image);
    jj_consume_token(2);
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 11:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_7;
      }
      jj_consume_token(11);
      jj_consume_token(2);
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case IDENTIFIER:
          ;
          break;
        default:
          jj_la1[7] = jj_gen;
          break label_8;
        }
        attribute = Attribute();
                                               entity.addAttribute(attribute);
      }
      jj_consume_token(3);
    }
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 12:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_9;
      }
      jj_consume_token(12);
      jj_consume_token(2);
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case IDENTIFIER:
          ;
          break;
        default:
          jj_la1[9] = jj_gen;
          break label_10;
        }
        tBehaviour = jj_consume_token(IDENTIFIER);
        jj_consume_token(4);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NUMBER:
          tWeight = jj_consume_token(NUMBER);
          break;
        case DECIMAL:
          tWeight = jj_consume_token(DECIMAL);
          break;
        default:
          jj_la1[10] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(6);
     entity.addBehaviour(tBehaviour.image, Double.parseDouble(tWeight.image));
      }
      jj_consume_token(3);
    }
    jj_consume_token(3);
    {if (true) return entity;}
    throw new Error("Missing return statement in function");
  }

  final public java.util.Map.Entry<String,String> Attribute() throws ParseException {
  Token tKey;
  Token tValue;
    tKey = jj_consume_token(IDENTIFIER);
    jj_consume_token(4);
    tValue = jj_consume_token(VALUE);
    jj_consume_token(6);
    {if (true) return new java.util.AbstractMap.SimpleEntry<String,String >(tKey.image,tValue.image);}
    throw new Error("Missing return statement in function");
  }

  final public BehaviourOptions Behaviour() throws ParseException {
  BehaviourOptions options = new BehaviourOptions();
  BehaviourOperation operation;
  BehaviourAttribute attribute;
  Token tName;
  Token tView;
    jj_consume_token(13);
    tName = jj_consume_token(IDENTIFIER);
                                       options.setName(tName.image);
    jj_consume_token(2);
    jj_consume_token(14);
    jj_consume_token(4);
    tView = jj_consume_token(IDENTIFIER);
                                      options.setLocale(tView.image);
    jj_consume_token(6);
    label_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 15:
      case 16:
      case 17:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_11;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 15:
        jj_consume_token(15);
        jj_consume_token(4);
        operation = BehaviourOperation();
        jj_consume_token(6);
                                                         options.setOperation(operation);
        break;
      case 16:
        jj_consume_token(16);
        jj_consume_token(4);
        attribute = BehaviourAttribute();
        jj_consume_token(6);
                                                         options.setAttribute(attribute);
        break;
      case 17:
        jj_consume_token(17);
        jj_consume_token(4);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 18:
          jj_consume_token(18);
                        options.setPanic(true);
          break;
        case 19:
          jj_consume_token(19);
                                                                options.setPanic(false);
          break;
        default:
          jj_la1[12] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(6);
        break;
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(3);
    {if (true) return options;}
    throw new Error("Missing return statement in function");
  }

  final public BehaviourOperation BehaviourOperation() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 20:
      jj_consume_token(20);
  {if (true) return BehaviourOperation.Flank;}
      break;
    case 21:
      jj_consume_token(21);
  {if (true) return BehaviourOperation.Field;}
      break;
    case 22:
      jj_consume_token(22);
   {if (true) return BehaviourOperation.Closest;}
      break;
    case 23:
      jj_consume_token(23);
  {if (true) return BehaviourOperation.AverageFor;}
      break;
    case 24:
      jj_consume_token(24);
  {if (true) return BehaviourOperation.Wander;}
      break;
    case 25:
      jj_consume_token(25);
  {if (true) return BehaviourOperation.Force;}
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public BehaviourAttribute BehaviourAttribute() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 26:
      jj_consume_token(26);
    {if (true) return BehaviourAttribute.Position;}
      break;
    case 27:
      jj_consume_token(27);
    {if (true) return BehaviourAttribute.Vector;}
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public SwarmerParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[16];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2,0x400,0x2000,0x0,0x100,0x200,0x800,0x0,0x1000,0x0,0x0,0x38000,0xc0000,0x38000,0x3f00000,0xc000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x8,0x0,0x0,0x0,0x8,0x0,0x8,0x6,0x0,0x0,0x0,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public SwarmerParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public SwarmerParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new SwarmerParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public SwarmerParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SwarmerParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public SwarmerParser(SwarmerParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(SwarmerParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[37];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 16; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 37; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
