package com.fy.fyy.back.common;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.fy.fyy.back.action.ActionAnnotation;
import com.fy.fyy.back.action.ActionModel;
import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.permission.ModelNode;


public class PermissionUtil {

  private static List<ModelNode> modelNodeList;

  static {
    modelNodeList = getActionModelNodeList();
    Collections.sort( modelNodeList, new Comparator<ModelNode>() {

      @Override
      public int compare( ModelNode o1, ModelNode o2 ) {
        int i1 = Integer.valueOf( o1.getId() );
        int i2 = Integer.valueOf( o2.getId() );
        return i1 < i2 ? -1 : i1 > i2 ? 1 : 0;
      }
    } );
  }

  public static boolean isPermission( String id, Customer loginCustomer, Map<String, ModelNode> customerPermission ) {

    if ( loginCustomer.getName().equals( Constraint.ADMIN ) ) {
      return true;
    }

    ModelNode mn = null;
    if ( customerPermission != null && customerPermission.containsKey( id ) ) {
      mn = customerPermission.get( id );
    }

    if ( mn != null && mn.getState().isSelected() || hasChildSelected( id, customerPermission ) ) {
      return true;
    }
    else {
      return false;
    }
  }

  private static boolean hasChildSelected( String id, Map<String, ModelNode> customerPermission ) {
    boolean result = false;
    
    if ( StringUtils.isEmpty( id ) || customerPermission == null && customerPermission.size() == 0 ) return false;
    Iterator<String> iter = customerPermission.keySet().iterator();
    String modelId = id.substring( 0, id.length() - 5 );
    String mid = id.substring( id.length() - 5, id.length() - 3 );
    while ( iter.hasNext() ) {
      String key = iter.next();
      String modelId2 = key.substring( 0, key.length() - 5 );
      String mid2 = key.substring( key.length() - 5, key.length() - 3 );
      if ( modelId.equals( modelId2 ) ) {
        if ( Integer.valueOf( mid2 ) > Integer.valueOf( mid ) ) {
          result = customerPermission.get( key ).getState().isSelected();
          if ( result ) break;
        }
      }
    }
    return result;
  }

  public static List<ModelNode> getModelNodeCopyList() {
    List<ModelNode> result = new ArrayList<>();
    for ( ModelNode modelNode : modelNodeList ) {
      ModelNode mn = new ModelNode( modelNode.getId(), modelNode.getText(), modelNode.getParent(), modelNode.getHref() );
      result.add( mn );
    }
    return result;
  }

  public static List<ModelNode> getTopModelNodeCopyList() {
    List<ModelNode> result = new ArrayList<>();
    for ( ModelNode modelNode : modelNodeList ) {
      if ( StringUtils.isEmpty( modelNode.getParent() ) ) {
        ModelNode mn = new ModelNode( modelNode.getId(), modelNode.getText(), modelNode.getParent(), modelNode.getHref() );
        result.add( mn );
      }
    }
    return result;
  }

  public static String getJson( List<ModelNode> modelNodeList ) {
    for ( ModelNode modelNode : modelNodeList ) {
      if ( StringUtils.isEmpty( modelNode.getParent() ) ) modelNode.setParent( "#" );
    }

    return JSONArray.toJSONString( modelNodeList );
  }

  public static List<ModelNode> getObjFromJson( String modelNodeJson ) {

    return JSONArray.parseArray( modelNodeJson, ModelNode.class );

  }

  private static List<ModelNode> getActionModelNodeList() {
    List<ModelNode> result = new ArrayList<>();
    List<String> classNames = getClassName( Constraint.PKG_ACTION, true );
    if ( classNames != null ) {
      for ( String className : classNames ) {
        Class actionClazz = null;
        try {
          actionClazz = Class.forName( className );
        }
        catch ( ClassNotFoundException e ) {
          e.printStackTrace();
          continue;
        }
        if ( actionClazz.getSuperclass().getName().equals( "com.fy.fyy.back.action.BaseAction" ) ) {

          ActionAnnotation clazzAnnotation = (ActionAnnotation)actionClazz.getAnnotation( ActionAnnotation.class );
          String clazzName = actionClazz.getSimpleName();
          String suffix = clazzName.substring( 0, clazzName.length() - 6 ) + ".Action";
          if ( clazzAnnotation != null ) {
            ActionModel actionModel = clazzAnnotation.name();
            String uri = "list/" + suffix;
            ModelNode mn = new ModelNode( actionModel.getId().toString(), actionModel.getName(), actionModel.getParentId() == null ? null : actionModel.getParentId().toString(),
                uri );

            result.add( mn );
          }
          Method[] methods = actionClazz.getDeclaredMethods();
          for ( int i = 0; i < methods.length; i++ ) {
            ActionAnnotation methodAnnotation = (ActionAnnotation)methods[i].getAnnotation( ActionAnnotation.class );
            if ( methodAnnotation == null ) continue;
            ActionModel methodActionModel = methodAnnotation.name();
            String methodName = methods[i].getName();
            String methodUri = methodName + "/" + suffix;
            ModelNode methodMn = new ModelNode( methodActionModel.getId().toString(), methodActionModel.getName(),
                methodActionModel.getParentId() == null ? null : methodActionModel.getParentId().toString(), methodUri );

            result.add( methodMn );

          }
        }
      }
    }

    return result;
  }

  /**
   * 获取某包下（包括该包的所有子包）所有类
   * 
   * @param packageName
   *            包名
   * @return 类的完整名称
   */
  private static List<String> getClassName( String packageName ) {
    return getClassName( packageName, true );
  }

  /**
   * 获取某包下所有类
   * 
   * @param packageName
   *            包名
   * @param childPackage
   *            是否遍历子包
   * @return 类的完整名称
   */
  private static List<String> getClassName( String packageName, boolean childPackage ) {
    List<String> fileNames = null;
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    String packagePath = packageName.replace( ".", "/" );
    URL url = loader.getResource( packagePath );
    if ( url != null ) {
      String type = url.getProtocol();
      if ( type.equals( "file" ) ) {
        fileNames = getClassNameByFile( url.getPath(), null, childPackage );
      }
      else if ( type.equals( "jar" ) ) {
        fileNames = getClassNameByJar( url.getPath(), childPackage );
      }
    }
    else {
      fileNames = getClassNameByJars( ( (URLClassLoader)loader ).getURLs(), packagePath, childPackage );
    }
    return fileNames;
  }

  /**
   * 从项目文件获取某包下所有类
   * 
   * @param filePath
   *            文件路径
   * @param className
   *            类名集合
   * @param childPackage
   *            是否遍历子包
   * @return 类的完整名称
   */
  private static List<String> getClassNameByFile( String filePath, List<String> className, boolean childPackage ) {
    filePath = filePath.replace( "%20", " " );
    List<String> myClassName = new ArrayList<String>();
    File file = new File( filePath );
    File[] childFiles = file.listFiles();
    if ( childFiles == null ) return myClassName;
    for ( File childFile : childFiles ) {
      if ( childFile.isDirectory() ) {
        if ( childPackage ) {
          myClassName.addAll( getClassNameByFile( childFile.getPath(), myClassName, childPackage ) );
        }
      }
      else {
        String childFilePath = childFile.getPath();
        if ( childFilePath.endsWith( ".class" ) ) {

          childFilePath = childFilePath.substring( childFilePath.indexOf( File.separator + "classes" ) + 9, childFilePath.lastIndexOf( "." ) );
          childFilePath = childFilePath.replace( File.separator, "." );
          myClassName.add( childFilePath );
        }
      }
    }

    return myClassName;
  }

  /**
   * 从jar获取某包下所有类
   * 
   * @param jarPath
   *            jar文件路径
   * @param childPackage
   *            是否遍历子包
   * @return 类的完整名称
   */
  private static List<String> getClassNameByJar( String jarPath, boolean childPackage ) {
    List<String> myClassName = new ArrayList<String>();
    String[] jarInfo = jarPath.split( "!" );
    String jarFilePath = jarInfo[0].substring( jarInfo[0].indexOf( "/" ) );
    String packagePath = jarInfo[1].substring( 1 );
    try {
      JarFile jarFile = new JarFile( jarFilePath );
      Enumeration<JarEntry> entrys = jarFile.entries();
      while ( entrys.hasMoreElements() ) {
        JarEntry jarEntry = entrys.nextElement();
        String entryName = jarEntry.getName();
        if ( entryName.endsWith( ".class" ) ) {
          if ( childPackage ) {
            if ( entryName.startsWith( packagePath ) ) {
              entryName = entryName.replace( "/", "." ).substring( 0, entryName.lastIndexOf( "." ) );
              myClassName.add( entryName );
            }
          }
          else {
            int index = entryName.lastIndexOf( "/" );
            String myPackagePath;
            if ( index != -1 ) {
              myPackagePath = entryName.substring( 0, index );
            }
            else {
              myPackagePath = entryName;
            }
            if ( myPackagePath.equals( packagePath ) ) {
              entryName = entryName.replace( "/", "." ).substring( 0, entryName.lastIndexOf( "." ) );
              myClassName.add( entryName );
            }
          }
        }
      }
    }
    catch ( Exception e ) {
      e.printStackTrace();
    }
    return myClassName;
  }

  /**
   * 从所有jar中搜索该包，并获取该包下所有类
   * 
   * @param urls
   *            URL集合
   * @param packagePath
   *            包路径
   * @param childPackage
   *            是否遍历子包
   * @return 类的完整名称
   */
  private static List<String> getClassNameByJars( URL[] urls, String packagePath, boolean childPackage ) {
    List<String> myClassName = new ArrayList<String>();
    if ( urls != null ) {
      for ( int i = 0; i < urls.length; i++ ) {
        URL url = urls[i];
        String urlPath = url.getPath();
        // 不必搜索classes文件夹
        if ( urlPath.endsWith( "classes/" ) ) {
          continue;
        }
        String jarPath = urlPath + "!/" + packagePath;
        myClassName.addAll( getClassNameByJar( jarPath, childPackage ) );
      }
    }
    return myClassName;
  }
}
