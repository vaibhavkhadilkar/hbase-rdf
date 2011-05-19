/*
 * Copyright © 2010 Talis Systems Ltd.
 *
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
 */

package com.talis.hbase.rdf.assembler;

import com.hp.hpl.jena.assembler.Assembler;
import com.hp.hpl.jena.assembler.Mode;
import com.hp.hpl.jena.assembler.assemblers.AssemblerBase;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.util.graph.GraphUtils;
import com.talis.hbase.rdf.StoreDesc;
import com.talis.hbase.rdf.connection.HBaseRdfConnectionDesc;

public class StoreDescAssembler extends AssemblerBase implements Assembler
{
    @Override
    public StoreDesc open( Assembler a, Resource root, Mode mode )
    {
        HBaseRdfConnectionDesc hbaseConnDesc = null ;
        Resource c = GraphUtils.getResourceValue( root, AssemblerVocab.pConnection ) ;
        if ( c != null )
            hbaseConnDesc = (HBaseRdfConnectionDesc)a.open( c ) ;
        
        String layoutName = GraphUtils.getStringValue( root, AssemblerVocab.pLayout ) ;
        String name = GraphUtils.getStringValue( root, AssemblerVocab.pName ) ;
        
        StoreDesc storeDesc = new StoreDesc( layoutName, name ) ; 
        storeDesc.connDesc = hbaseConnDesc ;
        
        return storeDesc;
    }    
}