import React, { Component } from 'react';
import PropTypes from 'prop-types';

class Game extends Component {
    constructor(){

    }

    render(){
      let pixels = null;

      for(j=0;j<this.props.height;j++){
        for(i=0;i<this.props.width;i++){
          pixels+=<div 
            style={{width:this.props.pixelSize, 
            height:this.props.pixelSize, 
            backgroundColor: this.currentState[j*this.props.width+i] }}/>
        }
      }
    }
}

Game.propTypes = {
  currentState: PropTypes.array,
  history: PropTypes.array,
  currentStep: PropTypes.number,
  width: PropTypes.number,
  height: PropTypes.number,
  pixelSize: PropTypes.number
};