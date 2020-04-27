/*
 * Copyright 2003-2005 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.hcis.ipanther.core.tree.model;





/**
* * 1000
   |-- 3000
   |-- |--4000
   |-- |--6000 
   |-- |-- |-- 6100
   |-- |-- |-- 6200 
   |-- |--6700
   |-- |--7000
   |-- |-- |--8000
   |-- |-- |-- |--9000
   |-- |-- |-- |--10000   
   |-- 5000 
 * @author <a href="mailto:banqiao@jdon.com">banq </a>
 *  
 */
public class StringTreeWalkerTest{
   /* StringTreeWalker longTreeWalker;
    StringTreeWalker stringTreeWalker;
    
     * @see TestCase#setUp()
     
    protected void setUp() throws Exception {
        super.setUp();
        //12 = 11 +1 , actual is 11, construtor must be 11 + 1
        TreeModel treeModel = new TreeModel("aaa", 10);

        treeModel.addChild("aaa", "aaa1");
        treeModel.addChild("aaa", "aaa2");
        treeModel.addChild("aaa", "aaa3");
        treeModel.addChild("aaa1", "aaa9");
        treeModel.addChild("aaa1", "aaa4");
        treeModel.addChild("aaa2", "aaa5");
        treeModel.addChild("aaa2", "aaa6");
        treeModel.addChild("aaa3", "aaa7");
        treeModel.addChild("aaa3", "aaa8");
        TreeModel treeModel = new TreeModel("1",5);
        treeModel.addChild("1","edd5fb898fb44ca49c5fead162c52f9c");
        treeModel.addChild("1","82103d6b39094f229e9b21829ddc597c");
        treeModel.addChild("1","8243e798d38543b3a9134eb9d0575c8b");
        treeModel.addChild("edd5fb898fb44ca49c5fead162c52f9c","0f8c42b276504044aefa05eea9da8e30");

        longTreeWalker = new StringTreeWalker(treeModel);
        stringTreeWalker = new StringTreeWalker(treeModel);
   	 	
   	 
    }
    
    public void forumPostSubTree(String parentId){
    	if(!stringTreeWalker.isLeaf(parentId)){
   	 		String[] children = stringTreeWalker.getChildren(parentId);
   	 		for(String child:children){
   	 			System.out.println(child);	
   	 			forumPostSubTree(child);
   	 			if(!stringTreeWalker.isLeaf(parentId)){
   	 				System.out.println(child);	
   	 			}
   	 		}   	 		
   	 	}else{
   	 		//System.out.println(parentId);
   	 	}
    }
    
    public void testKeys(){
    	for(String key:longTreeWalker.keys){
    		System.out.print(key+",");
    	}
    	forumPostSubTree("edd5fb898fb44ca49c5fead162c52f9c");
    
    }*/

/*    public void testGetParent() {
        String result = longTreeWalker.getParent("aaa2");
        assertEquals(result, "aaa");
    }

    public void testGetChild() {
        String result = longTreeWalker.getChild("aaa1", 1);
        assertEquals(result, "aaa4");
    }

    public void testGetChildCount() {
        int result = longTreeWalker.getChildCount("aaa1");        
        System.out.println("result=" + result);
        assertEquals(result, 2);
    }

    public void testGetChildren() {
        String[] childern = longTreeWalker.getChildren("aaa2");
        for(int i=0; i<childern.length; i ++){
            System.out.println(" child=" + childern[i]);
        }
    }

    public void testGetIndexOfChild() {
        int index = longTreeWalker.getIndexOfChild("aaa2", 6200);
        System.out.println("index=" + index);
        assertEquals(index, 1);
    }

    public void testGetDepth() {
        int index = longTreeWalker.getDepth("aaa7");
        System.out.println("index=" + index);
        assertEquals(index, 4);
    }

    public void testGetRecursiveChildren() {
        System.out.println("RecursiveChildren Error, pass by");
    }

    public void testIsLeaf() {
        boolean result = longTreeWalker.isLeaf("aaa1");
        assertEquals(result, false);
        
        result = longTreeWalker.isLeaf("aaa4");
        assertEquals(result, true);
        
        result = longTreeWalker.isLeaf("aaa3");
        assertEquals(result, false);
        
        result = longTreeWalker.isLeaf("aaa8");
        assertEquals(result, true);
    }
*/
}
