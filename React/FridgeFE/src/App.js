import React from 'react';
import Service from './Service';
import './CSS/App.css'
import {Container, Header} from 'semantic-ui-react'

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {

    }
  }

  render() {
    return (
      <div>  
        <nav class="navbar">
          <div class="navContainer">
            <Container>
              <h4 class="navbar-text">오늘 뭐해먹지?</h4>
            </Container>
          </div>
        </nav>
        <Container>
          <Service/>
        </Container>
      </div>
    );
  }
}


export default App;
