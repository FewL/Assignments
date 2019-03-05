package com.example.thegame;

import java.util.LinkedList;
import java.util.List;

public class LinkSearch {
    private static boolean MatchBlock(LinkInterface[][] datas,
                                      final Point srcPt, final Point dstPt) {
        //如果不属于0折连接则返回false
        if(srcPt.x!=dstPt.x&&srcPt.y!=dstPt.y)
            return false;

        int min,max;

        //如果两点的x坐标相等，则在竖直方向上扫描
        if(srcPt.x== dstPt.x){
            min = srcPt.y < dstPt.y ? srcPt.y : dstPt.y;
            max = srcPt.y > dstPt.y ? srcPt.y : dstPt.y;
            for(min++;min<max;min++)
            {
                if(!datas[srcPt.x][min].isEmpty())
                    return false;
            }
        }
        //如果两点的y坐标相等，则在水平方向上扫描
        else{
            min = srcPt.x < dstPt.x ? srcPt.x : dstPt.x;
            max = srcPt.x > dstPt.x ? srcPt.x : dstPt.x;
            for(min++;min<max;min++){
                if(!datas[min][srcPt.y].isEmpty())
                    return false;
            }
        }
        return true;
    }

    private static Point MatchBlockOne(LinkInterface[][] datas,
                                       final Point srcPt,final Point dstPt)
    {
        //如果不属于1折链接则返回null
        if(srcPt.x== dstPt.x||srcPt.y==dstPt.y)
            return null;

        //测试对角点1
        Point pt = new Point(srcPt.x,dstPt.y);

        if(datas[pt.x][pt.y].isEmpty())
        {
            boolean stMatch = MatchBlock(datas,srcPt,pt);
            boolean tdMatch = stMatch?MatchBlock(datas,pt,dstPt):stMatch;   //判断双重flag
            if(stMatch&&tdMatch){
                return pt;
            }
        }

        // 测试对角点2
        pt = new Point(dstPt.x, srcPt.y);

        if(datas[pt.x][pt.y].isEmpty()) {
            boolean stMatch = MatchBlock(datas, srcPt, pt);
            boolean tdMatch = stMatch ?
                    MatchBlock(datas, pt, dstPt) : stMatch;
            if (stMatch && tdMatch) {
                return pt;
            }
        }
        return null;
    }

    //2折连同，实则可判断三种情况
    public static List<Point> MatchBlockTwo(LinkInterface[][] datas,
                                            final Point srcPt,final Point dstPt)
    {
        if(datas==null||datas.length==0)
            return null;
        if(srcPt.x<0||srcPt.x>datas.length)
            return null;
        if(srcPt.y<0||srcPt.y>datas[0].length)
            return null;
        if(dstPt.x<0||dstPt.x>datas.length)
            return null;
        if(dstPt.y<0||dstPt.y>datas[0].length)
            return null;

        //判断0折连接
        if(MatchBlock(datas,srcPt,dstPt)){
            return new LinkedList<>();
        }

        List<Point> list = new LinkedList<Point>();
        Point point;

        //判断1折连接
        if((point = MatchBlockOne(datas,srcPt,dstPt))!=null)
        {
            list.add(point);
            return list;
        }

        //判断2折连接
        int i;
        for(i=srcPt.y+1;i<datas[srcPt.x].length;i++)
        {
            if(datas[srcPt.x][i].isEmpty()){
                Point src = new Point(srcPt.x,i);
                Point dst = MatchBlockOne(datas,src,dstPt);
                if(dst!=null){
                    list.add(src);
                    list.add(dst);
                    return list;
                }
            }else break;
        }

        for(i = srcPt.y - 1; i > -1; i--) {
            if(datas[srcPt.x][i].isEmpty()) {
                Point src = new Point(srcPt.x, i);
                Point dest = MatchBlockOne(datas, src, dstPt);
                if(dest != null) {
                    list.add(src);
                    list.add(dest);
                    return list;
                }
            } else break;
        }
        for(i = srcPt.x + 1; i < datas.length; i++) {
            if(datas[i][srcPt.y].isEmpty()) {
                Point src = new Point(i, srcPt.y);
                Point dest = MatchBlockOne(datas, src, dstPt);
                if(dest != null) {
                    list.add(src);
                    list.add(dest);
                    return list;
                }
            } else break;
        }

        for(i = srcPt.x - 1; i > -1; i--) {
            if(datas[i][srcPt.y].isEmpty()) {
                Point src = new Point(i, srcPt.y);
                Point dest = MatchBlockOne(datas, src, dstPt);
                if(dest != null) {
                    list.add(src);
                    list.add(dest);
                    return list;
                }
            } else break;
        }
        return null;
    }
}

