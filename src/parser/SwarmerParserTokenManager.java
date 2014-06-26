/* Generated By:JavaCC: Do not edit this line. SwarmerParserTokenManager.java */
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

/** Token Manager. */
public class SwarmerParserTokenManager implements SwarmerParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x1fffffea2L) != 0L)
         {
            jjmatchedKind = 40;
            return 8;
         }
         return -1;
      case 1:
         if ((active0 & 0x1fffffea2L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 1;
            return 8;
         }
         return -1;
      case 2:
         if ((active0 & 0x1fffffea2L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 2;
            return 8;
         }
         return -1;
      case 3:
         if ((active0 & 0x1000000L) != 0L)
         {
            if (jjmatchedPos < 2)
            {
               jjmatchedKind = 40;
               jjmatchedPos = 2;
            }
            return -1;
         }
         if ((active0 & 0x1fef77e80L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               jjmatchedKind = 40;
               jjmatchedPos = 3;
            }
            return 8;
         }
         if ((active0 & 0x88022L) != 0L)
            return 8;
         return -1;
      case 4:
         if ((active0 & 0x1000000L) != 0L)
         {
            if (jjmatchedPos < 2)
            {
               jjmatchedKind = 40;
               jjmatchedPos = 2;
            }
            return -1;
         }
         if ((active0 & 0x1b8637880L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 4;
            return 8;
         }
         if ((active0 & 0x46940602L) != 0L)
            return 8;
         return -1;
      case 5:
         if ((active0 & 0x1000000L) != 0L)
         {
            if (jjmatchedPos < 2)
            {
               jjmatchedKind = 40;
               jjmatchedPos = 2;
            }
            return -1;
         }
         if ((active0 & 0x98437080L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 5;
            return 8;
         }
         if ((active0 & 0x120200800L) != 0L)
            return 8;
         return -1;
      case 6:
         if ((active0 & 0x1000000L) != 0L)
         {
            if (jjmatchedPos < 2)
            {
               jjmatchedKind = 40;
               jjmatchedPos = 2;
            }
            return -1;
         }
         if ((active0 & 0x18000000L) != 0L)
            return 8;
         if ((active0 & 0x80437080L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 6;
            return 8;
         }
         return -1;
      case 7:
         if ((active0 & 0x1000000L) != 0L)
         {
            if (jjmatchedPos < 2)
            {
               jjmatchedKind = 40;
               jjmatchedPos = 2;
            }
            return -1;
         }
         if ((active0 & 0x80000080L) != 0L)
            return 8;
         if ((active0 & 0x437000L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 7;
            return 8;
         }
         return -1;
      case 8:
         if ((active0 & 0x1000000L) != 0L)
         {
            if (jjmatchedPos < 2)
            {
               jjmatchedKind = 40;
               jjmatchedPos = 2;
            }
            return -1;
         }
         if ((active0 & 0x437000L) != 0L)
            return 8;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 44:
         return jjStopAtPos(0, 8);
      case 58:
         return jjStopAtPos(0, 4);
      case 59:
         return jjStopAtPos(0, 6);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x10821000L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x6000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x8400000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x880L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x46100000L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x1000000L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x210000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x80040000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x220L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x100008002L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x20000400L);
      case 123:
         return jjStopAtPos(0, 2);
      case 125:
         return jjStopAtPos(0, 3);
      default :
         return jjMoveNfa_0(2, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x20140000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x100006000L);
      case 102:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000L);
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x4008422L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0xa800000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x880L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0xc1400000L);
      case 112:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x21000L);
      case 118:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000200L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000000L);
      case 100:
         return jjMoveStringLiteralDfa3_0(active0, 0x400L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x14018002L);
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000L);
      case 104:
         return jjMoveStringLiteralDfa3_0(active0, 0x6000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x20440000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x8800000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000L);
      case 116:
         return jjMoveStringLiteralDfa3_0(active0, 0x1021880L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
      case 122:
         return jjMoveStringLiteralDfa3_0(active0, 0x20L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x6000L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000L);
      case 100:
         return jjMoveStringLiteralDfa4_0(active0, 0x20400000L);
      case 101:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(3, 5, 8);
         else if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(3, 19, 8);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x80040880L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000L);
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x2800000L);
      case 112:
         return jjMoveStringLiteralDfa4_0(active0, 0x200L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x10031000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x8300000L);
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000400L);
      case 119:
         if ((active0 & 0x8000L) != 0L)
         {
            jjmatchedKind = 15;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x2L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x11010000L);
      case 99:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(4, 18, 8);
         break;
      case 100:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(4, 26, 8);
         break;
      case 101:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(4, 9, 8);
         else if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(4, 20, 8);
         else if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(4, 23, 8);
         else if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(4, 30, 8);
         return jjMoveStringLiteralDfa5_0(active0, 0x28200000L);
      case 104:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(4, 10, 8);
         break;
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x421000L);
      case 107:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(4, 25, 8);
         break;
      case 111:
         return jjMoveStringLiteralDfa5_0(active0, 0x100000000L);
      case 115:
         if ((active0 & 0x2L) != 0L)
            return jjStartNfaWithStates_0(4, 1, 8);
         break;
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x80000880L);
      case 118:
         return jjMoveStringLiteralDfa5_0(active0, 0x6000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 98:
         return jjMoveStringLiteralDfa6_0(active0, 0x21000L);
      case 103:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000000L);
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x80006080L);
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000L);
      case 114:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(5, 29, 8);
         else if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(5, 32, 8);
         break;
      case 115:
         return jjMoveStringLiteralDfa6_0(active0, 0x8000000L);
      case 116:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(5, 21, 8);
         return jjMoveStringLiteralDfa6_0(active0, 0x410000L);
      case 121:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(5, 11, 8);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(6, 28, 8);
         return jjMoveStringLiteralDfa7_0(active0, 0x80L);
      case 105:
         return jjMoveStringLiteralDfa7_0(active0, 0x410000L);
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x81006000L);
      case 116:
         if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(6, 27, 8);
         break;
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x21000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(7, 31, 8);
         return jjMoveStringLiteralDfa8_0(active0, 0x1000000L);
      case 111:
         return jjMoveStringLiteralDfa8_0(active0, 0x410000L);
      case 115:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(7, 7, 8);
         break;
      case 116:
         return jjMoveStringLiteralDfa8_0(active0, 0x21000L);
      case 117:
         return jjMoveStringLiteralDfa8_0(active0, 0x6000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x20000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 8;
         }
         else if ((active0 & 0x1000000L) != 0L)
            return jjStopAtPos(8, 24);
         return jjMoveStringLiteralDfa9_0(active0, 0x1000L);
      case 110:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(8, 16, 8);
         else if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(8, 22, 8);
         break;
      case 114:
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 8;
         }
         return jjMoveStringLiteralDfa9_0(active0, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 115:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(9, 12, 8);
         else if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(9, 13, 8);
         break;
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 16;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 39)
                        kind = 39;
                     jjCheckNAddStates(0, 3);
                  }
                  else if (curChar == 45)
                     jjCheckNAddStates(4, 6);
                  else if (curChar == 34)
                     jjCheckNAddTwoStates(10, 11);
                  else if (curChar == 46)
                  {
                     if (kind > 38)
                        kind = 38;
                     jjCheckNAddStates(7, 9);
                  }
                  else if (curChar == 47)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 0:
                  if (curChar != 47)
                     break;
                  if (kind > 37)
                     kind = 37;
                  jjCheckNAdd(1);
                  break;
               case 1:
                  if ((0xfffffffffffffbffL & l) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  jjCheckNAdd(1);
                  break;
               case 3:
                  if (curChar != 46)
                     break;
                  if (kind > 38)
                     kind = 38;
                  jjCheckNAddStates(7, 9);
                  break;
               case 4:
                  if (curChar == 45)
                     jjCheckNAddTwoStates(5, 3);
                  break;
               case 5:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(5, 3);
                  break;
               case 6:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 38)
                     kind = 38;
                  jjCheckNAddStates(10, 13);
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 9:
                  if (curChar == 34)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 10:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 11:
                  if (curChar == 34 && kind > 41)
                     kind = 41;
                  break;
               case 12:
                  if (curChar == 45)
                     jjCheckNAddStates(4, 6);
                  break;
               case 13:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 39)
                     kind = 39;
                  jjCheckNAddTwoStates(14, 13);
                  break;
               case 14:
                  if (curChar == 45)
                     jjCheckNAdd(13);
                  break;
               case 15:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 39)
                     kind = 39;
                  jjCheckNAddStates(0, 3);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(8);
                  break;
               case 1:
                  if (kind > 37)
                     kind = 37;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 8:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 40)
                     kind = 40;
                  jjCheckNAdd(8);
                  break;
               case 10:
                  jjAddStates(14, 15);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 10:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(14, 15);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 16 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   5, 3, 14, 13, 5, 3, 13, 4, 3, 6, 4, 5, 3, 6, 10, 11, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", "\166\151\145\167\163", "\173", "\175", "\72", "\163\151\172\145", "\73", 
"\145\156\164\151\164\151\145\163", "\54", "\163\150\141\160\145", "\167\151\144\164\150", 
"\145\156\164\151\164\171", "\141\164\164\162\151\142\165\164\145\163", 
"\142\145\150\141\166\151\157\165\162\163", "\142\145\150\141\166\151\157\165\162", "\166\151\145\167", 
"\157\160\145\162\141\164\151\157\156", "\141\164\164\162\151\142\165\164\145", "\160\141\156\151\143", 
"\164\162\165\145", "\146\141\154\163\145", "\157\146\146\163\145\164", 
"\143\157\156\144\151\164\151\157\156", "\141\154\157\156\145", "\156\157\164\55\141\154\157\156\145", 
"\146\154\141\156\153", "\146\151\145\154\144", "\143\154\157\163\145\163\164", 
"\141\166\145\162\141\147\145", "\167\141\156\144\145\162", "\146\157\162\143\145", 
"\160\157\163\151\164\151\157\156", "\166\145\143\164\157\162", null, null, null, null, null, null, null, null, 
null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x3c1ffffffffL, 
};
static final long[] jjtoSkip = {
   0x3e00000000L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[16];
private final int[] jjstateSet = new int[32];
protected char curChar;
/** Constructor. */
public SwarmerParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public SwarmerParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 16; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
